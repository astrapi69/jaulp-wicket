package de.alpharogroup.wicket.components.sign.in.password.change;

import lombok.Getter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public abstract class ChangePasswordFormPanel extends BasePanel<ChangePasswordModel> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private Label buttonLabel;

	@Getter
	private Button submitButton;

	@Getter
	private Form<?> form;

	public ChangePasswordFormPanel(String id, IModel<ChangePasswordModel> model) {
		super(id, model);
		form = newForm("form", model);
		add(form);
		form.add(new ChangePasswordPanel("changePasswordPanel", model));
		// Create submit button for the form
		submitButton = newButton("submitButton");			
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel", 
				"global.update.button.label",
				"Update"));	
		form.add(submitButton);
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
	protected Form<?> newForm(String id, IModel<?> model) {
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a Button.
	 * 
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newButton(String id) {
		return new Button(id) {
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onChangePassword();
			}
		};
	}

	/**
	 * Factory method for creating the Button Label. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a Label.
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
		return ComponentFactory.newLabel(id, ResourceModelFactory
				.newResourceModel(resourceKey, this, defaultValue));
	}
	
	protected abstract void onChangePassword();

}
