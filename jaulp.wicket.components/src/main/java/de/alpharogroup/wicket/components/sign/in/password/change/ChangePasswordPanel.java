package de.alpharogroup.wicket.components.sign.in.password.change;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;

/**
 * The Class AbstractChangePasswordPanel.
 *
 * @author Asterios Raptis
 */
public class ChangePasswordPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/** The current password. */
	private final Component currentPassword;
	
	/**
	 * Gets the current password.
	 *
	 * @return the current password
	 */
	public Component getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the new password
	 */
	public Component getNewPassword() {
		return newPassword;
	}

	/**
	 * Gets the repeat new password.
	 *
	 * @return the repeat new password
	 */
	public Component getRepeatNewPassword() {
		return repeatNewPassword;
	}

	/** The new password. */
	private final Component newPassword;
	
	/** The repeat new password. */
	private final Component repeatNewPassword;

	/**
	 * Instantiates a new abstract change password panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public ChangePasswordPanel(final String id, final IModel<ChangePasswordModel> model) {
		super(id);		
		add(currentPassword = newCurrentPasswordTextField("currentPassword", model));
		add(newPassword = newPasswordTextField("newPassword", model));
		add(repeatNewPassword = newRepeatPasswordTextField("repeatNewPassword", model));
	}

	/**
	 * Factory method for creating the PasswordTextField for the repeat password. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a PasswordTextField for the repeat password.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text field
	 */
	protected Component newRepeatPasswordTextField(String id, final IModel<ChangePasswordModel> model) {
		IModel<String> labelModel = ResourceModelFactory.newResourceModel("change.pw.new.password.repeat.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel("global.enter.new.password.repeat.label", this);
		LabeledPasswordTextFieldPanel<ChangePasswordModel> pwTextField = new LabeledPasswordTextFieldPanel<ChangePasswordModel>(id, model, labelModel) {
			private static final long serialVersionUID = 1L;
			@Override
			protected PasswordTextField newPasswordTextField(String id,
					IModel<ChangePasswordModel> model) {
				PasswordTextField pwTextField = new PasswordTextField(id, model(from(model).getRepeatNewPassword()));
				pwTextField.setOutputMarkupId(true);
				if(placeholderModel != null) {
					pwTextField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return pwTextField;
			}
		};
		return pwTextField;
	}

	/**
	 * Factory method for creating the PasswordTextField for the new password. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a PasswordTextField for the new password.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text field
	 */
	protected Component newPasswordTextField(String id, final IModel<ChangePasswordModel> model) {
		IModel<String> labelModel = ResourceModelFactory.newResourceModel("change.pw.new.password.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel("global.enter.new.password.label", this);
		LabeledPasswordTextFieldPanel<ChangePasswordModel> pwTextField = new LabeledPasswordTextFieldPanel<ChangePasswordModel>(id, model, labelModel){
			private static final long serialVersionUID = 1L;
			@Override
			protected PasswordTextField newPasswordTextField(String id,
					IModel<ChangePasswordModel> model) {
				PasswordTextField pwTextField = new PasswordTextField(id, model(from(model).getNewPassword()));
				pwTextField.setOutputMarkupId(true);
				if(placeholderModel != null) {
					pwTextField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return pwTextField;
			}
		};
		return pwTextField;
	}

	/**
	 * Factory method for creating the PasswordTextField for the current password. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a PasswordTextField for the current password.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text field
	 */
	protected Component newCurrentPasswordTextField(String id, final IModel<ChangePasswordModel> model) {
		IModel<String> labelModel = ResourceModelFactory.newResourceModel("change.pw.current.password.label", this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel("global.enter.current.password.label", this);
		LabeledPasswordTextFieldPanel<ChangePasswordModel> pwTextField = new LabeledPasswordTextFieldPanel<ChangePasswordModel>(id, model, labelModel){
			private static final long serialVersionUID = 1L;
			@Override
			protected PasswordTextField newPasswordTextField(String id,
					IModel<ChangePasswordModel> model) {
				PasswordTextField pwTextField = new PasswordTextField(id, model(from(model).getCurrentPassword()));
				pwTextField.setOutputMarkupId(true);
				if(placeholderModel != null) {
					pwTextField.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return pwTextField;
			}
		};
		return pwTextField;
	}

}
