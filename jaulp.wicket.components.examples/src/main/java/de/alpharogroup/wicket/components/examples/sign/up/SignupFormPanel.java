package de.alpharogroup.wicket.components.examples.sign.up;

import net.sourceforge.jaulp.auth.models.BaseUsernameSignUpModel;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.sign.up.SignupPanel;

public class SignupFormPanel  extends BasePanel<BaseUsernameSignUpModel> {
	private static final long serialVersionUID = 1L;

	public SignupFormPanel(String id) {
		super(id);
		BaseUsernameSignUpModel modelObject = new BaseUsernameSignUpModel();
		modelObject.setEmail("");
		IModel<BaseUsernameSignUpModel> model = new CompoundPropertyModel<BaseUsernameSignUpModel>(
				modelObject);
		setModel(model);
		Form<BaseUsernameSignUpModel> form = new Form<>("form", model);
		addOrReplace(form);
		form.addOrReplace(new SignupPanel("signupPanel", getModel()));
		// Create submit button for the form
		final Button submitButton = new Button("signupButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSignup();
			}

		};
		form.addOrReplace(submitButton);

	}
	
	protected void onSignup() {
		BaseUsernameSignUpModel model = (BaseUsernameSignUpModel) getDefaultModelObject();
		System.out.println(model);
	}
}
