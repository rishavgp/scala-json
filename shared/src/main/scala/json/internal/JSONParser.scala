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

package json.internal

import scala.collection.immutable.StringOps

trait JSONParser {

  case class JSONBuilderSettings(
      spaceString: String = " ", ignoreNulls: Boolean = true,
      newLineString: String = "\n", tabString: String = "  ") {
    def nTabs(n: Int) = if (tabString == "") ""
    else (for (i <- 0 until n) yield tabString).mkString
  }

  val prettyJSONBuilder = JSONBuilderSettings()
  val denseJSONBuilder = JSONBuilderSettings(
    newLineString = "", tabString = "", spaceString = "")



}