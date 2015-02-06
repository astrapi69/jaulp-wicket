package de.alpharogroup.wicket.components.search;

import lombok.Getter;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public abstract class SimpleSearchPanel extends BasePanel<SimpleSearchModel>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private final Label buttonLabel;
	@Getter
	private final Form<?> form;
	@Getter
	private final Component searchtext;
	@Getter
	private final Button searchButton;

	public SimpleSearchPanel(final String id)
	{
		super(id);
		setModel(new CompoundPropertyModel<>(new SimpleSearchModel()));
		add(form = newForm("form", getModel()));
		form.add(searchtext = newTextField("searchtext"));
		searchButton = newButton("searchButton");
		searchButton.add(buttonLabel = newButtonLabel("buttonLabel", "global.button.search",
			"Search"));
		form.add(searchButton);
	}

	/**
	 * Factory method for creating the TextField. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a TextField.
	 * 
	 * @param id
	 *            the id
	 * @return the TextField
	 */
	protected Component newTextField(String id)
	{
		return ComponentFactory.newTextField(id);
	}

	/**
	 * Factory method for creating the Form. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<?> newForm(String id, IModel<? extends SimpleSearchModel> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 * 
	 * @param id
	 *            the id
	 * @return the component
	 */
	protected Button newButton(String id)
	{
		return new Button(id)
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				onSearch();
			}
		};
	}

	/**
	 * Factory method for creating the button Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a button
	 * Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @return the label
	 */
	protected Label newButtonLabel(String id, final String resourceKey, final String defaultValue)
	{
		return ComponentFactory.newLabel(id, resourceKey, defaultValue, this);
	}

	protected abstract void onSearch();
}