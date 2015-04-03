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
package de.alpharogroup.wicket.components.deregistration;

import net.sourceforge.jaulp.collections.ListUtils;
import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.content.ContentModel;
import de.alpharogroup.wicket.components.i18n.content.ContentPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;

/**
 * The Class DeregistrationPanel.
 *
 * @author Asterios Raptis
 */
public abstract class DeregistrationPanel extends BasePanel<DeregistrationModel>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	private Label buttonLabel;

	private Button submitButton;

	private Component motivation;

	private Component contentPanel;

	private Form<?> form;

	/**
	 * Instantiates a new deregistration panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public DeregistrationPanel(final String id, final IModel<DeregistrationModel> model)
	{
		super(id, model);
	}


	public void onBeforeRender()
	{
		addOrReplace(contentPanel = newContentPanel("contentPanel"));
		addOrReplace(form = newForm("form", getModel()));
		form.addOrReplace(motivation = newMotivation("motivation", getModel()));
		// Create submit button for the form
		submitButton = newButton("submitButton");
		submitButton.addOrReplace(buttonLabel = newButtonLabel("buttonLabel",
			"sem.main.global.deregistration.user.label", "Deregister"));
		form.addOrReplace(submitButton);
		super.onBeforeRender();
	}

	protected Component newContentPanel(String id)
	{
		ContentPanel contentPanel = new ContentPanel("contentPanel", Model.of(ContentModel
			.builder()
			.headerResourceKey(
				ResourceBundleKey.builder().key("sem.main.info.frame.deregistration.user.label")
					.parameters(ListUtils.toObjectArray(getDomainName())).build())
			.contentResourceKey(
				ResourceBundleKey.builder().key("sem.main.info.frame.deregistration.user.label")
					.parameters(ListUtils.toObjectArray(getDomainName())).build()).build()));
		contentPanel.getHeader().add(new AddJsQueryBehavior("wrap", "<h1></h1>"));
		contentPanel.getContent().add(new AddJsQueryBehavior("wrap", "<p class=\"lead\"></p>"));
		return contentPanel;

	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @return the label
	 */
	protected Label newLabel(String id, final ResourceBundleKey resourceKey)
	{
		return ComponentFactory.newLabel(id, resourceKey, this);
	}

	/**
	 * Factory method for creating the LabeledTextAreaPanel. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a Form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected LabeledTextAreaPanel<?> newMotivation(String id, IModel<DeregistrationModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("sem.main.feedback.deregistration.user.label")
				.defaultValue("Please confirm the deregistration")
				.parameters(ListUtils.toObjectArray(getDomainName())).build(), this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.deregistration.motivation.label", this,
			"Enter here your deregistration motivation.");
		LabeledTextAreaPanel<DeregistrationModel> description = new LabeledTextAreaPanel<DeregistrationModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected TextArea<DeregistrationModel> newTextArea(String id,
				IModel<DeregistrationModel> model)
			{
				TextArea<DeregistrationModel> textArea = super.newTextArea(id, model);
				if (placeholderModel != null)
				{
					textArea.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return super.newTextArea(id, model);
			}
		};
		return description;
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
	protected Form<?> newForm(String id, IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
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
				onDeregistration();
			}
		};
	}

	/**
	 * Factory method for creating the Button Label. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a Label.
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
		return ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(resourceKey, this, defaultValue));
	}

	public Label getButtonLabel()
	{
		return buttonLabel;
	}

	public Button getSubmitButton()
	{
		return submitButton;
	}

	public Component getMotivation()
	{
		return motivation;
	}

	public Component getContentPanel()
	{
		return contentPanel;
	}

	public Form<?> getForm()
	{
		return form;
	}

	public abstract void onDeregistration();

	public abstract String getDomainName();

}
