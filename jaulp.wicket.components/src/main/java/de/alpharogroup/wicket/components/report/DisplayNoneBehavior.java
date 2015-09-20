package de.alpharogroup.wicket.components.report;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

/**
 * The Class {@link DisplayNoneBehavior}.
 */
public class DisplayNoneBehavior extends AttributeModifier
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link DisplayNoneBehavior}.
	 */
	public DisplayNoneBehavior()
	{
		super("style", Model.of("display: none"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTemporary(final Component component)
	{
		return true;
	}
}