/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.dropdownchoices.pages;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements Serializable
{

	public static Map<String, List<String>> modelsMap;

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public static Map<String, List<String>> initializeModelMap()
	{
		if (modelsMap == null)
		{
			/** The models map. */
			modelsMap = new HashMap<String, List<String>>();

			modelsMap.put("trademark.audi",
				Arrays.asList(new String[] { "audi.a4", "audi.a6", "audi.tt" }));
			modelsMap.put(
				"trademark.cadillac",
				Arrays.asList(new String[] { "cadillac.cts", "cadillac.dts", "cadillac.escalade",
						"cadillac.srx", "cadillac.deville" }));
			modelsMap.put(
				"trademark.ford",
				Arrays.asList(new String[] { "ford.crown", "ford.escape", "ford.expedition",
						"ford.explorer", "ford.f_150" }));
		}
		return modelsMap;
	}
}
