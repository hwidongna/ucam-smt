/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use these files except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright 2014 - Juan Pino, Aurelien Waite, William Byrne
 *******************************************************************************/
package uk.ac.cam.eng.rulebuilding.features;

import org.apache.hadoop.io.Text;

/**
 * 
 * @author Aurelien Waite
 * @date 28 May 2014
 */
public enum EnumRuleType {
	EXTRACTED("0"), ASCII_OOV_DELETE("-1"), GLUE("");

	private EnumRuleType(String lhs) {
		this.lhs = new Text(lhs);
	}

	private final Text lhs;

	public Text getLhs() {
		return new Text(lhs);
	}
}
