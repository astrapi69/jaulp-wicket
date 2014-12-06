package de.alpharogroup.wicket.components.factory;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

public class ComponentFactory {

	/**
	 * Factory method for creating the Label with a {@link ResourceBundleKey}.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component to find resource keys
	 * @return the label
	 */
	public static Label newLabel(String id, final ResourceBundleKey resourceKey, final Component component) {
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(resourceKey, component);		
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * Factory method for creating the Label with the for attribute.
	 * 
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	public static Label newLabel(String id, String forId, IModel<String> model) {
		Label label = new Label(id, model);
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		label.setOutputMarkupId(true);
		return label;
	}
	


	/**
	 * Factory method for creating the Label with a {@link PropertyModel}.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	public static <T> Label newLabel(String id, PropertyModel<T> model) {
		Label label = new Label(id, model);
		label.setOutputMarkupId(true);
		return label;
	}
	
	/**
	 * Factory method for creating the ComponentFeedbackPanel.
	 * 
	 * @param id
	 *            the id
	 * @param filter
	 *            the filter
	 * @return the component feedback panel
	 */
	public static ComponentFeedbackPanel newComponentFeedbackPanel(String id,
			Component filter) {
		ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id,
				filter);
		feedbackPanel.setOutputMarkupId(true);
		return feedbackPanel;
	}
	
}
