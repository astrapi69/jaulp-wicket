package de.alpharogroup.wicket.components.examples.sign.up;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsUtils;

import de.alpharogroup.auth.models.BaseUsernameSignUpModel;
import de.alpharogroup.auth.models.SignInModel;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledTextFieldPanel;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;
import de.alpharogroup.wicket.components.sign.up.SignupFormPanel;
import de.alpharogroup.wicket.components.sign.up.SignupPanel;

public class SignupExamplesPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int labelSize = 4;
	private int inputSize = 8;

	public SignupExamplesPanel(final String id, final IModel<BaseUsernameSignUpModel> model)
	{
		super(id, model);

		final IModel<BaseUsernameSignUpModel> cpm = new CompoundPropertyModel<BaseUsernameSignUpModel>(
			model);
		SignupFormPanel signFormPanel = new SignupFormPanel("horizantalFormPanel", cpm)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Form<?> newForm(String id, IModel<? extends BaseUsernameSignUpModel> model)
			{
				Form<?> form = super.newForm(id, model);
				form.add(new AttributeAppender("class", " form-horizontal col-sm"+(labelSize-inputSize)));
				return form;
			}

			@Override
			protected Component newSignupPanel(final String id, final IModel<BaseUsernameSignUpModel> model)
			{
				return new SignupPanel(id, model)
				{
					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unchecked")
					@Override
					protected Component newUsernameTextField(final String id,
						final IModel<BaseUsernameSignUpModel> model)
					{
						LabeledTextFieldPanel<BaseUsernameSignUpModel> nameTextField = (LabeledTextFieldPanel<BaseUsernameSignUpModel>)
							super.newUsernameTextField(id, model);
						nameTextField.add(new AttributeAppender("class", " form-group"));
						nameTextField
						.getTextField()
						.add(
							new JqueryStatementsBehavior()
								.add(new BuildableChainableStatement.Builder().label("wrap")
									.args(JsUtils.quotes("<div class=\"col-sm-" + inputSize + "\"></div>"))
									.build()))
						.add(new AttributeAppender("class", " form-control"));
						nameTextField.getLabelComponent().add(
						new AttributeAppender("class", " control-label col-sm-" + labelSize));
						nameTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return nameTextField;
					}
					@Override
					protected Component newSigninPanel(String id,
						IModel<? extends BaseUsernameSignUpModel> model)
					{
						SigninPanel signinPanel = new SigninPanel(id, model)
						{
							/**
							 * The serialVersionUID
							 */
							private static final long serialVersionUID = 1L;

							@SuppressWarnings("unchecked")
							@Override
							protected Component newEmailTextField(String id, IModel<SignInModel> model)
							{
								final LabeledEmailTextFieldPanel<SignInModel> emailTextField = (LabeledEmailTextFieldPanel<SignInModel>)
									super
									.newEmailTextField(id, model);
								emailTextField.add(new AttributeAppender("class", " form-group"));
								emailTextField
									.getEmailTextField()
									.add(
										new JqueryStatementsBehavior()
											.add(new BuildableChainableStatement.Builder().label("wrap")
												.args(JsUtils.quotes("<div class=\"col-sm-" + inputSize + "\"></div>"))
												.build()))
									.add(new AttributeAppender("class", " form-control"));
								emailTextField.getLabelComponent().add(
									new AttributeAppender("class", " control-label col-sm-" + labelSize));
								return emailTextField;
							}

							@SuppressWarnings("unchecked")
							@Override
							protected Component newPasswordTextField(String id, IModel<SignInModel> model)
							{
								final LabeledPasswordTextFieldPanel<SignInModel> pwTextField = (LabeledPasswordTextFieldPanel<SignInModel>)super
									.newPasswordTextField(id, model);
								pwTextField.add(new AttributeAppender("class", " form-group"));
								pwTextField
									.getPasswordTextField()
									.add(
										new JqueryStatementsBehavior()
											.add(new BuildableChainableStatement.Builder().label("wrap")
												.args(JsUtils.quotes("<div class=\"col-sm-" + inputSize + "\"></div>"))
												.build()))
									.add(new AttributeAppender("class", " form-control"));
								pwTextField.getLabelComponent().add(
									new AttributeAppender("class", " control-label col-sm-" + labelSize));
								return pwTextField;
							}
						};
						return signinPanel;
					}
					@SuppressWarnings("unchecked")
					@Override
					protected Component newRepeatPasswordTextField(String id,
						IModel<BaseUsernameSignUpModel> model)
					{
						LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel> pwTextField = (LabeledPasswordTextFieldPanel<BaseUsernameSignUpModel>)
							super.newRepeatPasswordTextField(id, model);
						pwTextField.add(new AttributeAppender("class", " form-group"));
						pwTextField
							.getPasswordTextField()
							.add(
								new JqueryStatementsBehavior()
									.add(new BuildableChainableStatement.Builder().label("wrap")
										.args(JsUtils.quotes("<div class=\"col-sm-" + inputSize + "\"></div>"))
										.build()))
							.add(new AttributeAppender("class", " form-control"));
						pwTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return pwTextField;
					}
				};
			}

			@Override
			protected Button newButton(String id)
			{
				Button button = super.newButton(id);
				button.add(
					new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
						.label("wrap").args(JsUtils.quotes("<div class=\"form-group\"></div>"))
						.build())).add(
					new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
						.label("wrap")
						.args(
							JsUtils.quotes("<div class=\"col-sm-offset-" + labelSize + " col-sm-" + inputSize + "\"></div>")).build()));
				button.add(new AttributeAppender("class", " btn btn-default"));
				return button;
			}

			@Override
			protected void onSignup(final AjaxRequestTarget target, final Form<?> form)
			{
				target.add(getFeedback());
				info("Email: " + getModelObject().getEmail() + 
					":\nUsername:" + getModelObject().getUsername() +
					":\nPassword:" + getModelObject().getPassword() + 
					":\nRepeatPassword:"	+ getModelObject().getRepeatPassword());
			}

		};
		add(signFormPanel);
	}

	protected Component getFeedback()
	{
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

}
