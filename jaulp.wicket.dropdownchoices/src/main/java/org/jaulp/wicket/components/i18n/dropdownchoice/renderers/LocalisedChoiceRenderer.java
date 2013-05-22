package org.jaulp.wicket.components.i18n.dropdownchoice.renderers;


import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class LocalisedChoiceRenderer.
 * 
 * @author Asterios Raptis
 */
public class LocalisedChoiceRenderer implements IChoiceRenderer<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The properties key prefix. */
	private String propertiesKeyPrefix;

	/** The relative component used for lookups. */
	private Component component;

	/** The component class. */
	private Class<?> componentClass;

	/**
	 * Instantiates a new localised choice renderer.
	 * 
	 * @param propertiesKeyPrefix
	 *            the properties key prefix
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public LocalisedChoiceRenderer(final String propertiesKeyPrefix,
			final Component component, final Class<?> componentClass) {
		this.propertiesKeyPrefix = propertiesKeyPrefix;
		this.component = component;
		this.componentClass = componentClass;
	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param object
	 *            the object
	 * @return the display value
	 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getDisplayValue(java.lang.Object)
	 */
	@Override
	public Object getDisplayValue(final String object) {
		StringResourceModel resourceModel = new StringResourceModel(
				propertiesKeyPrefix + "." + object, component,  null);
		String value = resourceModel.getObject();
		return value;

	}

	/**
	 * {@inheritDoc}.
	 * 
	 * @param object
	 *            the object
	 * @param index
	 *            the index
	 * @return the id value
	 * @see org.apache.wicket.markup.html.form.IChoiceRenderer#getIdValue(java.lang.Object,
	 *      int)
	 */
	@Override
	public String getIdValue(final String object, final int index) {
		return object;
	}
}