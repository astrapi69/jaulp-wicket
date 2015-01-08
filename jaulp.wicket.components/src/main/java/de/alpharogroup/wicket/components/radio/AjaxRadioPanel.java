package de.alpharogroup.wicket.components.radio;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class AjaxRadioPanel.
 *
 * @param <T> the generic type
 */
public abstract class AjaxRadioPanel<T extends Serializable> extends
		BasePanel<List<T>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	@Getter
	private Form<?> form;
	
	/**
	 * Gets the radio group.
	 *
	 * @return the radio group
	 */
	@Getter	
	private RadioGroup<T> radioGroup;

	/**
	 * Instantiates a new ajax radio panel.
	 *
	 * @param id the id
	 * @param items the items
	 * @param propertyExpression the property expression
	 */
	public AjaxRadioPanel(String id, List<T> items, String propertyExpression) {
		this(id, items, null, propertyExpression);
	}

	/**
	 * Instantiates a new ajax radio panel.
	 *
	 * @param id the id
	 * @param items the items
	 * @param currentSelection the current selection
	 * @param labelPropertyExpression the label property expression
	 */
	public AjaxRadioPanel(String id, List<T> items, T currentSelection,
			String labelPropertyExpression) {
		super(id);
		add(form = newForm("form"));
		form.add(radioGroup = newRadioGroup("radioGroup", new Model<T>(currentSelection)));
		radioGroup.add(newRadios(radioGroup, items, labelPropertyExpression));
	}

	/**
	 * Factory method for creating a new AjaxRadio.
	 *
	 * @param id the id
	 * @param group the group
	 * @param item the item
	 * @return the ajax radio
	 */
	protected AjaxRadio<T> newAjaxRadio(String id, final RadioGroup<T> group,
			ListItem<T> item) {
		return new AjaxRadio<T>("radio", item.getModel()) {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick(AjaxRequestTarget target) {
				onRadioSelect(target, group.getModelObject());
			}
		};
	}
	
	/**
	 * New form.
	 *
	 * @param id the id
	 * @return the form
	 */
	protected Form<?> newForm(String id) {
		return ComponentFactory.newForm(id);
	}
	
	/**
	 * New radio group.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the radio group
	 */
	protected RadioGroup<T> newRadioGroup(String id, IModel<T> model) {
		RadioGroup<T> radioGroup = new RadioGroup<T>(id, model);
		return radioGroup;
	}

	/**
	 * New radios.
	 *
	 * @param group the group
	 * @param items the items
	 * @param labelPropertyExpression the label property expression
	 * @return the component
	 */
	protected Component newRadios(final RadioGroup<T> group, List<T> items,
			final String labelPropertyExpression) {
		return new ListView<T>("radioButtons", items) {
			private static final long serialVersionUID = 1L;
			protected void populateItem(ListItem<T> item) {
				AjaxRadio<T> radio = newAjaxRadio("radio", group, item);
				Label label = ComponentFactory.newLabel("label", radio.getMarkupId(), new PropertyModel<String>(item
						.getModel(), labelPropertyExpression));
				item.add(radio);
				item.add(label);
			}
		};
	}

	/**
	 * Implement this method to provide special behavior when an radio button is selected.
	 *
	 * @param target the target
	 * @param newSelection the new selection
	 */
	protected abstract void onRadioSelect(AjaxRequestTarget target,
			T newSelection);

	/**
	 * {@inheritDoc}
	 */
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptHeaderItem
				.forReference(new JavaScriptResourceReference(AjaxRadioPanel.class,
						"AjaxRadioPanel.js")));
	}
}