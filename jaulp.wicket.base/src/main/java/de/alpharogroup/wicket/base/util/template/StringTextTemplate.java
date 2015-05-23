package de.alpharogroup.wicket.base.util.template;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.wicket.util.io.Streams;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.util.string.interpolator.MapVariableInterpolator;
import org.apache.wicket.util.template.TextTemplate;

/**
 * A {@link TextTemplate} that is build from a String.
 */
public class StringTextTemplate extends TextTemplate
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The LOGGER. */
	static final Logger LOGGER = LoggerFactory.getLogger(StringTextTemplate.class.getName());

	/** The content of this template. */
	private final StringBuilder buffer = new StringBuilder();

	/**
	 * Creates a new instance of a {@link StringTextTemplate}.
	 * 
	 * @param content
	 *            The content of the template.
	 */
	public StringTextTemplate(final String content)
	{
		this(content, "text", "UTF-8");
	}

	/**
	 * Creates a new instance of a {@link StringTextTemplate}.
	 * 
	 * @param content
	 *            The content of the template.
	 * @param contentType
	 *            The content type.
	 * @param encoding
	 *            the file's encoding
	 */
	public StringTextTemplate(final String content, final String contentType, final String encoding)
	{
		super(contentType);
		final IResourceStream stream = new StringResourceStream(content, getContentType());
		try
		{
			if (encoding != null)
			{
				buffer.append(Streams.readString(stream.getInputStream(), encoding));
			}
			else
			{
				buffer.append(Streams.readString(stream.getInputStream()));
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
		catch (ResourceStreamNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				stream.close();
			}
			catch (IOException e)
			{
				LOGGER.error("" + e.getMessage(), e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getString()
	{
		return buffer.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TextTemplate interpolate(final Map<String, ?> variables)
	{
		if (variables != null)
		{
			String result = new MapVariableInterpolator(buffer.toString(), variables).toString();
			buffer.delete(0, buffer.length());
			buffer.append(result);
		}
		return this;
	}
}
