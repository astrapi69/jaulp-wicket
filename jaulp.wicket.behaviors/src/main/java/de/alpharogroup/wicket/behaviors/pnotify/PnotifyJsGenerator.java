package de.alpharogroup.wicket.behaviors.pnotify;

import java.util.Map;

import lombok.Getter;

import org.apache.wicket.request.resource.ResourceReference;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class PnotifyJsGenerator generates the javascript with a PnotifySettings object.
 */
public class PnotifyJsGenerator extends JavascriptGenerator<PnotifySettings>
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
	private PnotifySettings pnotifySettings;


	public PnotifyJsGenerator(PnotifySettings settings)
	{
		super(settings);
	}

	public static void main(String... args)
	{
		PnotifySettings pnotifySettings = new PnotifySettings();
		pnotifySettings.getTitle().setValue("Test title");
		pnotifySettings.getText().setValue("a text");

		PnotifyJsGenerator pnotifyJsGenerator = new PnotifyJsGenerator(pnotifySettings);
		String result = pnotifyJsGenerator.generateJs(pnotifySettings);

		System.out.println(result);
		System.out.println("================================");

		StackSettings stackSettings = new StackSettings();
		stackSettings.getDir2().setValue("right");
		pnotifySettings.getStack().setValue(stackSettings.asJavascriptArray());

		result = pnotifyJsGenerator.generateJs(pnotifySettings);

		System.out.println(result);
	}

	public String generateJs(PnotifySettings settings)
	{
		return generateJs(settings, "PNotify");
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
		if (!getSettings().getStack().isDefaultValue())
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
