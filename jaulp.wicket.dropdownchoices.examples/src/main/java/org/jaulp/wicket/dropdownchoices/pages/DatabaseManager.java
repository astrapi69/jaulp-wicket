package org.jaulp.wicket.dropdownchoices.pages;

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
