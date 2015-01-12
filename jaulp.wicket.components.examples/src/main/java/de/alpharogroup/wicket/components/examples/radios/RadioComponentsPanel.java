package de.alpharogroup.wicket.components.examples.radios;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.CssResourceReference;

public class RadioComponentsPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public RadioComponentsPanel(String id) {
		super(id);
		
		AjaxRadioExamplePanel radioButtonPanel = new AjaxRadioExamplePanel("ajaxRadioExampleButtonPanel", null) {

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			public void renderHead(IHeaderResponse response) {
				response.render(CssHeaderItem.forReference(new CssResourceReference(
						AjaxRadioExamplePanel.class, "AjaxRadioPanel.css")));

			}
		};
		radioButtonPanel.getAjaxRadioPanel().add(new AttributeAppender("class", "radioButtonPanel"));
		add(radioButtonPanel);
		
		add(new RadioChoicePanel("radioChoicePanel", null));
		
		add(new RadioGroupExamplePanel("radioGroupPanel", null));

		add(new RadioChoicesListViewExamplePanel("radioChoicesListViewExamplePanel", null));
	}

}
