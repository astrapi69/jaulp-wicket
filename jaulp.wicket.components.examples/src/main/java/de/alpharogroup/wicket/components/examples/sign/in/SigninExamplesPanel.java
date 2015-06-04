/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.examples.sign.in;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.javascript.JsUtils;

import de.alpharogroup.auth.models.SignInModel;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.behaviors.wrappers.Wrappers;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledEmailTextFieldPanel;
import de.alpharogroup.wicket.components.labeled.textfield.LabeledPasswordTextFieldPanel;
import de.alpharogroup.wicket.components.sign.in.SignInWithRedirectionBean;
import de.alpharogroup.wicket.components.sign.in.SigninPanel;
import de.alpharogroup.wicket.components.sign.in.form.SigninFormPanel;

public class SigninExamplesPanel extends GenericPanel<SignInWithRedirectionBean>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int labelSize = 2;
	private int inputSize = 4;

	public SigninExamplesPanel(final String id, final IModel<SignInWithRedirectionBean> model)
	{
		super(id, model);
		add(newSigninFormPanel("horizantalFormPanel", model));
	}

	protected Component newSigninFormPanel(final String id,
		final IModel<SignInWithRedirectionBean> model)
	{
		final SigninFormPanel<SignInWithRedirectionBean> signFormPanel = new SigninFormPanel<SignInWithRedirectionBean>(
			id, new CompoundPropertyModel<SignInWithRedirectionBean>(model))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected Form<?> newForm(String id, IModel<?> model)
			{
				Form<?> form = super.newForm(id, model);
				form.add(new AttributeAppender("class", " form-horizontal"));
				return form;
			}

			@Override
			protected Button newButton(String id)
			{
				Button button = super.newButton(id);
				button.add(Wrappers.FORM_GROUP_ELEMENT).add(
					new JqueryStatementsBehavior().add(new BuildableChainableStatement.Builder()
						.label("wrap")
						.args(
							JsUtils.quotes("<div class=\"col-sm-offset-" + labelSize + " col-sm-"
								+ inputSize + "\"></div>")).build()));
				button.add(new AttributeAppender("class", " btn btn-default"));
				return button;
			}

			@Override
			protected MarkupContainer newPasswordForgottenLink(String id,
				IModel<SignInWithRedirectionBean> model)
			{
				MarkupContainer passwordForgottenLink = super.newPasswordForgottenLink(id, model);
				passwordForgottenLink.add(new AttributeAppender("class", " btn btn-link"));
				return passwordForgottenLink;
			}

			@Override
			protected Component newSigninPanel(String id, IModel<? extends SignInModel> model)
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
						final LabeledEmailTextFieldPanel<SignInModel> emailTextField = (LabeledEmailTextFieldPanel<SignInModel>)super
							.newEmailTextField(id, model);
						emailTextField.add(new AttributeAppender("class", " form-group"));
						emailTextField
							.getEmailTextField()
							.add(
								new JqueryStatementsBehavior()
									.add(new BuildableChainableStatement.Builder()
										.label("wrap")
										.args(
											JsUtils.quotes("<div class=\"col-sm-" + inputSize
												+ "\"></div>")).build()))
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
									.add(new BuildableChainableStatement.Builder()
										.label("wrap")
										.args(
											JsUtils.quotes("<div class=\"col-sm-" + inputSize
												+ "\"></div>")).build()))
							.add(new AttributeAppender("class", " form-control"));
						pwTextField.getLabelComponent().add(
							new AttributeAppender("class", " control-label col-sm-" + labelSize));
						return pwTextField;
					}
				};
				return signinPanel;
			}

			@Override
			protected void onSignin(AjaxRequestTarget target, Form<?> form)
			{
				target.add(getFeedback());
				info("Email: " + getModelObject().getEmail() + "\nPassword:"
					+ getModelObject().getPassword());
			}

			@Override
			protected void onPasswordForgotten(AjaxRequestTarget target, Form<?> form)
			{
				target.add(getFeedback());
				info("Email: " + getModelObject().getEmail() + "\nPassword:"
					+ getModelObject().getPassword());
			}

		};
		return signFormPanel;
	}

	protected Component getFeedback()
	{
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}

}
