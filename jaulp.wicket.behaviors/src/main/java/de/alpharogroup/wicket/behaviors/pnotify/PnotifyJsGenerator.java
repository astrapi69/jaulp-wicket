package de.alpharogroup.wicket.behaviors.pnotify;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.template.StringTextTemplate;
import de.alpharogroup.wicket.base.util.template.TextTemplateUtils;

/**
 * The Class PnotifyJsGenerator generates the javascript with a PnotifySettings object.
 */
public class PnotifyJsGenerator implements Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link org.apache.wicket.request.resource.ResourceReference} constant for the js and
	 * css-file from the Pnotify.
	 */
	public static final ResourceReference PNOTIFY_REFERENCE = PnotifyJsReference.INSTANCE;

	/**
	 * The pnotifySettings.
	 */
	@Getter
	private final PnotifySettings pnotifySettings;


	public PnotifyJsGenerator(final PnotifySettings pnotifySettings)
	{
		this.pnotifySettings = Args.notNull(pnotifySettings, "pnotifySettings");
	}

	public static void main(String... args)
	{
		PnotifySettings pnotifySettings = new PnotifySettings();
		pnotifySettings.getTitle().setValue("Test title");
		pnotifySettings.getText().setValue("a text");
		PnotifyJsGenerator pnotifyJsGenerator = new PnotifyJsGenerator(pnotifySettings);
		String result = pnotifyJsGenerator.generateJs(pnotifySettings);
		System.out.println(result);
	}


	/**
	 * Generate toastr js.
	 *
	 * @param pnotifySettings
	 *            the pnotifySettings
	 * @return the string
	 */
	public String generateJs(final PnotifySettings pnotifySettings)
	{
		// 1. Create an empty map...
		final Map<String, Object> variables = new HashMap<>();
		// 2. Initialize the map with the pnotifySettings...
		TextTemplateUtils.initializeVariables(variables, pnotifySettings.asSet());
		// 3. Generate the js template with the map and the method name...
		String stringTemplateContent = generateJavascriptTemplateContent(variables, "PNotify");
		// 4. Create the StringTextTemplate with the generated template...
		StringTextTemplate stringTextTemplate = new StringTextTemplate(stringTemplateContent);
		// 5. Interpolate the template with the values of the map...
		stringTextTemplate.interpolate(variables);
		// 6. return it as String...
		return stringTextTemplate.asString();
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
		if (!this.pnotifySettings.getStack().isDefaultValue())
		{
			String customStack = "customStack";
			String stack = (String)variables.get("stack");
			sb.append("var " + customStack + " = ");
			sb.append(stack);
			sb.append(";\n");
			variables.put("stack", customStack);
		}
		sb.append("new");
		sb.append(" ");
		sb.append(methodName);
		sb.append("(");
		if (0 < variables.size())
		{
			sb.append("{\n");
			int count = 1;
			for (Map.Entry<String, Object> entry : variables.entrySet())
			{
				String key = entry.getKey();
				sb.append(key + ": ${" + key + "}");
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
			sb.append("}");
		}
		sb.append(");");
		return sb.toString();
	}

}
