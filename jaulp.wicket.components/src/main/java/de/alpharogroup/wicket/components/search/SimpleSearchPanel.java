package de.alpharogroup.wicket.components.search;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

public abstract class SimpleSearchPanel extends BasePanel<SimpleSearchModel> {
	
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private final Label buttonLabel;
	private final Form<?> form;
	private final Component searchtext;

	private final Button searchButton;

	public SimpleSearchPanel(final String id) {
		super(id);
		setModel(new CompoundPropertyModel<SimpleSearchModel>(
				new SimpleSearchModel()));
		add(form = newForm("form", getModel()));
		form.add(searchtext = newTextField("searchtext"));
		searchButton = newButton("searchButton");
		searchButton.add(buttonLabel = newButtonLabel("buttonLabel",
				"global.button.search", "Search"));
		form.add(searchButton);
	}

	/**
	 * Factory method for creating the TextField. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a TextField.
	 * 
	 * @param id
	 *            the id
	 * @return the TextField
	 */
	protected Component newTextField(String id) {
		return new TextField<String>(id);
	}

	/**
	 * Factory method for creating the Form. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Form<?> newForm(String id,
			IModel<? extends SimpleSearchModel> model) {
		return new Form(id, model);
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Button.
	 * 
	 * @param id
	 *            the id
	 * @return the component
	 */
	protected Button newButton(String id) {
		return new Button(id) {
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSearch();
			}
		};
	}

	/**
	 * Factory method for creating the button Label. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a button Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the label
	 */
	protected Label newButtonLabel(String id, final String resourceKey,
			final String defaultValue) {
		final IModel<String> labelModel = ResourceModelFactory
				.newResourceModel(resourceKey, this, defaultValue);
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	public Label getButtonLabel() {
		return buttonLabel;
	}

	public Form<?> getForm() {
		return form;
	}

	public Component getSearchtext() {
		return searchtext;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	protected abstract void onSearch();
}