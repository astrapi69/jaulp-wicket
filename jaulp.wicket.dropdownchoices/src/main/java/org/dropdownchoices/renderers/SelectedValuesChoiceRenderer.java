package org.dropdownchoices.renderers;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.StringResourceModel;

/**
 * The Class SelectedValuesChoiceRenderer.
 * 
 * @author Asterios Raptis
 */
public class SelectedValuesChoiceRenderer implements IChoiceRenderer<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The relative component used for lookups. */
	private Component component;

	/** The component class. */
	private Class<?> componentClass;

	/**
	 * Instantiates a new selected values choice renderer.
	 * 
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public SelectedValuesChoiceRenderer(final Component component,
			final Class<?> componentClass) {
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
		String splitString = "=>";
		String[] splittedValue = object.split(splitString);
		StringBuffer sb = new StringBuffer();
		if (splittedValue.length == 1) {
			StringResourceModel resourceModel = new StringResourceModel(
					splittedValue[0], component, null);
			sb.append(resourceModel.getObject());
		} else {
			StringResourceModel resourceModel = new StringResourceModel(
					splittedValue[0], component, null);
			sb.append(resourceModel.getObject());
			sb.append(splitString);
			resourceModel = new StringResourceModel(splittedValue[1],
					component,  null);
			sb.append(resourceModel.getObject());
		}
		return sb.toString();
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

