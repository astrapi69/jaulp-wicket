package org.jaulp.wicket.components.i18n.dropdownchoice.renderers;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class PropertiesChoiceRenderer.
 * 
 * @author Asterios Raptis
 */
public class PropertiesChoiceRenderer implements IChoiceRenderer<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The relative component used for lookups. */
	private Component component;

	/** The component class. */
	private Class<?> componentClass;

	/**
	 * Instantiates a new properties choice renderer.
	 * 
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public PropertiesChoiceRenderer(final Component component,
			final Class<?> componentClass) {
		this.component = component;
		this.componentClass = componentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getDisplayValue(final String object) {
		StringResourceModel resourceModel = new StringResourceModel(
				object, component, null);
		String value = resourceModel.getObject();
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdValue(final String object, final int index) {
		return object;
	}
}