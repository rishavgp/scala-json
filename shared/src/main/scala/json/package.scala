/*
 * Copyright 2015 MediaMath, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import json.internal.JSONAnnotations

/**
 * Created by crgodsey on 2/14/15.
 */
package object json extends JSONAnnotations.TypeAdder {
  val JNaN = JNumber(Double.NaN)

  type JSONAccessor[T] = JSONAccessorProducer[T, JValue]

  def fromJSON[T](jval: JValue)(implicit acc: JSONAccessor[T]) =
    acc.fromJSON(jval)

  def toJSONString[T](obj: T)(implicit acc: JSONAccessor[T]) =
    obj.js.toString

  implicit class AnyValJSEx[T](val x: T) extends AnyVal {
    def js[U <: JValue](implicit acc: JSONProducer[T, U]): U = acc.createJSON(x)

    /*def js(implicit acc: CaseClassObjectAccessor[T]): JObject =
			acc.createJSON(x)*/
  }

  implicit class JSONStringOps(val str: String) extends AnyVal {
    def ->>(v: JValue): (JString, JValue) = JString(str) -> v

    def jValue = JValue.fromString(str)
  }

  private[json] def fieldCatch[T](name: String)(f: => T): T = try f catch {
    case e: InputFormatException =>
      throw e.prependFieldName(name)
    case e: Throwable =>
      throw GenericFieldException(name, e)
  }
}
