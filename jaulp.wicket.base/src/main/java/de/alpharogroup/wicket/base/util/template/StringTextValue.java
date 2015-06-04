package de.alpharogroup.wicket.base.util.template;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.apache.wicket.util.lang.Args;

/**
 * Value for the map of StringTextTemplate.
 *
 * @param <T>
 *            the generic type of the value.
 */
@Getter
public class StringTextValue<T> implements Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The value. */
	private T value;

	/** The default value. */
	private final T defaultValue;

	/**
	 * The initial value flag indicates if the initial value is also the default value. This flag is
	 * taken for the generation of javascript code, if false(default value is set) this
	 * {@link StringTextValue} will be ignored in the generation of javascript code to keep the
	 * generated code as small as possible.
	 */
	private boolean initialValue;

	/**
	 * Sets the quotation mark type.
	 *
	 * @param quotationMarkType
	 *            the quotation mark type
	 * @return the string text value
	 */
	@Setter
	@Accessors(chain = true)
	private QuotationMarkType quotationMarkType = QuotationMarkType.NONE;

	/** The type. */
	@NonNull
	private final StringTextType type;

	/** The name. */
	private final String name;

	/**
	 * Instantiates a new {@link StringTextValue} object.
	 *
	 * @param name
	 *            the name
	 * @param stringTextType
	 *            the string text type
	 */
	public StringTextValue(final String name, StringTextType stringTextType)
	{
		this(name, null, stringTextType, true);
	}

	/**
	 * Instantiates a new {@link StringTextValue} object.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param stringTextType
	 *            the string text type
	 */
	public StringTextValue(final String name, final T value, StringTextType stringTextType)
	{
		this(name, value, stringTextType, true);
	}

	/**
	 * Instantiates a new {@link StringTextValue} object.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param stringTextType
	 *            the string text type
	 * @param initialValue
	 *            the flag if the initial value is also the default value. This flag is taken for
	 *            the generation of javascript, if false this {@link StringTextValue} will be not
	 *            added.
	 */
	@SuppressWarnings("unchecked")
	public StringTextValue(final String name, final T value, StringTextType stringTextType,
		boolean initialValue)
	{
		Args.notNull(stringTextType, "type");
		this.value = value;
		this.defaultValue = value;
		this.type = stringTextType;
		this.initialValue = initialValue;
		this.name = name;
		if (stringTextType.equals(StringTextType.BOOLEAN))
		{
			this.value = (T)Boolean.FALSE;
		}
		if (stringTextType.equals(StringTextType.STRING)
			|| stringTextType.equals(StringTextType.STRING_INTEGER))
		{
			setQuotationMarkType(QuotationMarkType.SINGLE);
		}
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the value
	 * @return the string text value
	 */
	public StringTextValue<T> setValue(final T value)
	{
		this.initialValue = false;
		this.value = value;
		return this;
	}
}
