package org.jaulp.wicket.base.util.parameter;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.StringValueConversionException;

/**
 * The Class PageParametersUtils.
 */
public final class PageParametersUtils {

	/**
	 * Gets the parameter.
	 *
	 * @param parameters
	 *            the parameters
	 * @param name
	 *            the name
	 * @return the parameter
	 */
	public static String getParameter(final PageParameters parameters,
			String name) {
		return getString(parameters.get(name));
	}
	/**
	 * Gets the {@link String} object from the {@link StringValue} or returns null if the given {@link StringValue} is null.
	 *
	 * @param parameters
	 *            the parameters
	 * @param name
	 *            the name
	 * @return the parameter
	 */
	public static String getString(StringValue value) {
		if (isNotNullOrEmpty(value)) {
			return value.toString();
		}
		return null;
	}
	
	

	/**
	 * Gets the Integer object or returns null if the given StringValue is null
	 * or empty.
	 *
	 * @param stringValue
	 *            the user id as StringValue object
	 * @return the Integer object or null if the given StringValue is null or
	 *         empty.
	 */
	public static Integer getInteger(StringValue stringValue) {
		Integer value = null;
		if (isNotNullOrEmpty(stringValue)) {
			try {
				value = stringValue.toInteger();
			} catch (StringValueConversionException e) {
				value = -1;
			}
		}
		return value;
	}

	/**
	 * <p>
	 * Checks if the given StringValue is not null and the value of the given
	 * StringValue object is not null and the value of the given StringValue
	 * object is not empty.
	 * </p>
	 *
	 * @param stringValue
	 *            the StringValue to check, may be null
	 * @return <code>true</code> if the StringValue is not null and the value of
	 *         the given StringValue object is not null and the value of the
	 *         given StringValue object is not empty.
	 */
	public static final boolean isNotNullOrEmpty(StringValue stringValue) {
		return stringValue != null && !stringValue.isNull()
				&& !stringValue.isEmpty();
	}

	/**
	 * <p>
	 * Checks if the given StringValue is null or the value of the given
	 * StringValue object is null or the value of the given StringValue object
	 * is empty.
	 * </p>
	 *
	 * @param stringValue
	 *            the StringValue to check, may be null
	 * @return <code>true</code> if the StringValue is null or the value of the
	 *         given StringValue object is null or the value of the given
	 *         StringValue object is empty.
	 */
	public static final boolean isNullOrEmpty(StringValue stringValue) {
		return stringValue == null || stringValue.isNull()
				|| stringValue.isEmpty();
	}

}
