package de.alpharogroup.wicket.behaviors.spin;

import java.util.Map;

import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class SpinJsGenerator generates the javascript with a SpinSettings object and a given
 * component id.
 */
public class SpinJsGenerator extends JavascriptGenerator<SpinSettings>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the js var for the spinner.
	 */
	public static final String SPINNER_JS_VARIABLE = "spinner";

	/**
	 * Instantiates a new {@link SpinSettings}.
	 */
	public SpinJsGenerator()
	{
		this(new SpinSettings());
	}

	/**
	 * Instantiates a new {@link SpinSettings}.
	 *
	 * @param componentId
	 *            the component id
	 */
	public SpinJsGenerator(final String componentId)
	{
		this(new SpinSettings(), componentId);
	}

	/**
	 * Instantiates a new {@link SpinSettings}.
	 *
	 * @param settings
	 *            the settings for the spin library.
	 */
	public SpinJsGenerator(final SpinSettings settings)
	{
		this(settings, "");
	}

	/**
	 * Instantiates a new {@link SpinSettings}.
	 *
	 * @param settings
	 *            the settings for the spin library.
	 * @param componentId
	 *            the component id
	 */
	public SpinJsGenerator(final SpinSettings settings, final String componentId)
	{
		super(settings);
		setComponentId(Args.notNull(componentId, "componentId"));
		setWithComponentId(true);
	}


	/**
	 * Generates the javascript template code from the given map and the given method name that will
	 * be used to interpolate with the values of the given map.
	 *
	 * @param variables
	 *            the map with the javascript options.
	 * @param methodName
	 *            The method name.
	 * @return The generated javascript from the given map and the given method name.
	 */
	public String generateJavascriptTemplateContent(final Map<String, Object> variables,
		String methodName)
	{

		StringBuilder sb = new StringBuilder();
		if (1 < variables.size())
		{
			sb.append("var opts = ");
			sb.append("{\n");
			int count = 1;
			Object localComponentId = variables.get(COMPONENT_ID);
			variables.remove(COMPONENT_ID);
			for (Map.Entry<String, Object> entry : variables.entrySet())
			{
				String key = entry.getKey();
				sb.append(key).append(": ${").append(key).append("}");
				if (count < variables.size())
				{
					sb.append(",\n");
				}
				else
				{
					sb.append("\n");
				}
				count++;
			}
			variables.put(COMPONENT_ID, localComponentId);
			sb.append("};");
			sb.append("\n");
		}
		if (!getComponentId().isEmpty())
		{
			sb.append("var target = document.getElementById('${").append(COMPONENT_ID)
				.append("}');");
		}
		else
		{
			sb.append("var target = document.getElementsByTagName('BODY')[0];");
		}
		sb.append("var " + SPINNER_JS_VARIABLE + " = new Spinner(");
		if (1 < variables.size())
		{
			sb.append("opts");
		}
		sb.append(").spin(target);");
		return sb.toString();
	}
}
