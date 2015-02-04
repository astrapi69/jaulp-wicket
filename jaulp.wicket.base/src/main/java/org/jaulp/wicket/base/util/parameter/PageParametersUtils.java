package org.jaulp.wicket.base.util.parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.StringValueConversionException;

/**
 * The Class PageParametersUtils.
 */
public final class PageParametersUtils
{

	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(PageParametersUtils.class.getName());

	/**
	 * Gets the parameter.
	 *
	 * @param parameters
	 *            the parameters
	 * @param name
	 *            the name
	 * @return the parameter
	 */
	public static String getParameter(final PageParameters parameters, String name)
	{
		return getString(parameters.get(name));
	}

	/**
	 * Gets the string from the given {@link StringValue}.
	 *
	 * @param value
	 *            the value
	 * @return the string
	 */
	public static String getString(StringValue value)
	{
		if (isNotNullOrEmpty(value))
		{
			return value.toString();
		}
		return null;
	}

	/**
	 * Gets the Integer object or returns null if the given StringValue is null or empty.
	 *
	 * @param stringValue
	 *            the user id as StringValue object
	 * @return the Integer object or null if the given StringValue is null or empty.
	 * @deprecated use instead the {@link PageParametersUtils#toInteger(StringValue)}
	 */
	public static Integer getInteger(StringValue stringValue)
	{
		return toInteger(stringValue);
	}


	/**
	 * Gets the Integer object or returns null if the given StringValue is null or empty.
	 *
	 * @param stringValue
	 *            the user id as StringValue object
	 * @return the Integer object or null if the given StringValue is null or empty.
	 */
	public static Integer toInteger(StringValue stringValue)
	{
		Integer value = null;
		if (isNotNullOrEmpty(stringValue))
		{
			try
			{
				value = stringValue.toInteger();
			}
			catch (StringValueConversionException e)
			{
				LOGGER.error("Error by converting the given StringValue.", e);
			}
		}
		return value;
	}

	/**
	 * <p>
	 * Checks if the given StringValue is not null and the value of the given StringValue object is
	 * not null and the value of the given StringValue object is not empty.
	 * </p>
	 *
	 * @param stringValue
	 *            the StringValue to check, may be null
	 * @return <code>true</code> if the StringValue is not null and the value of the given
	 *         StringValue object is not null and the value of the given StringValue object is not
	 *         empty.
	 */
	public static final boolean isNotNullOrEmpty(StringValue stringValue)
	{
		return stringValue != null && !stringValue.isNull() && !stringValue.isEmpty();
	}

	/**
	 * <p>
	 * Checks if the given StringValue is null or the value of the given StringValue object is null
	 * or the value of the given StringValue object is empty.
	 * </p>
	 *
	 * @param stringValue
	 *            the StringValue to check, may be null
	 * @return <code>true</code> if the StringValue is null or the value of the given StringValue
	 *         object is null or the value of the given StringValue object is empty.
	 */
	public static final boolean isNullOrEmpty(StringValue stringValue)
	{
		return stringValue == null || stringValue.isNull() || stringValue.isEmpty();
	}

	/**
	 * Converts the given Map to a {@link PageParameters} object.
	 * 
	 * @param parameters
	 *            the {@link Map} with the parameters to set.
	 * @return the {@link PageParameters}
	 */
	public static PageParameters toPageParameters(Map<String, String> parameters)
	{
		PageParameters param = new PageParameters();
		for (Entry<String, String> parameter : parameters.entrySet())
		{
			param.add(parameter.getKey(), parameter.getValue());
		}
		return param;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 * 
	 * @param request
	 *            the request
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 */
	public static String getParameter(Request request, String parameterName)
	{
		String parameterValue = request.getRequestParameters().getParameterValue(parameterName)
			.toString();
		if (parameterValue == null || parameterValue.isEmpty())
		{
			parameterValue = request.getPostParameters().getParameterValue(parameterName)
				.toString();
		}
		return parameterValue;
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 * 
	 * @return a map with all parameters.
	 */
	public static Map<String, String[]> getParameterMap()
	{
		Request request = RequestCycle.get().getRequest();
		return getParameterMap(request);
	}

	/**
	 * Gets a map with all parameters. Looks in the query and post parameters. Migration method from
	 * 1.4.* to 1.5.*.
	 * 
	 * @param request
	 *            the request
	 * @return a map with all parameters.
	 */
	public static Map<String, String[]> getParameterMap(Request request)
	{
		IRequestParameters parameters = request.getRequestParameters();
		final Map<String, String[]> map = new HashMap<>();
		Set<String> parameterNames = parameters.getParameterNames();
		for (String parameterName : parameterNames)
		{
			List<StringValue> parameterValues = parameters.getParameterValues(parameterName);
			String[] stringArray = { };
			if (parameterValues != null && !parameterValues.isEmpty())
			{
				stringArray = new String[parameterValues.size()];
				for (int i = 0; i < parameterValues.size(); i++)
				{
					stringArray[i] = parameterValues.get(i).toString();
				}
			}
			map.put(parameterName, stringArray);
		}
		return map;
	}

	/**
	 * Gets the parameter value from given parameter name. Looks in the query and post parameters.
	 * 
	 * @param parameterName
	 *            the parameter name
	 * @return the parameter value
	 */
	public static String getParameter(String parameterName)
	{
		Request request = RequestCycle.get().getRequest();
		return getParameter(request, parameterName);
	}

}
