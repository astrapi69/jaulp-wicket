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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.resourcebundle.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.content.ContentModelBean;
import de.alpharogroup.wicket.components.i18n.content.ContentPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import lombok.Getter;

/**
 * The Class DeregistrationPanel.
 *
 * @author Asterios Raptis
 */
public abstract class DeregistrationPanel extends BasePanel<DeregistrationModelBean>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The button label. */
	@Getter
	private Label buttonLabel;
	/** The button. */
	@Getter
	private Button submitButton;
	/** The motivation Component. */
	@Getter
	private Component motivation;
	/** The content Component. */
	@Getter
	private Component contentPanel;
	/** The form. */
	@Getter
	private Form<?> form;

	/**
	 * Instantiates a new {@link DeregistrationPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public DeregistrationPanel(final String id, final IModel<DeregistrationModelBean> model)
	{
		super(id, model);
	}

	/**
	 * Gets the domain name.
	 *
	 * @return the domain name
	 */
	public abstract String getDomainName();

	/**
	 * Factory method for creating the Button. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Button.
	 *
	 * @param id
	 *            the wicket id
	 * @return the Button
	 */
	protected Button newButton(final String id)
	{
		return new Button(id)
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onSubmit()
			{
				onDeregistration(null);
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
	protected Label newButtonLabel(final String id, final String resourceKey,
		final String defaultValue)
	{
		return ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(resourceKey, this, defaultValue));
	}

	/**
	 * Factory method for creating the new {@link Component} of the content. This method is invoked
	 * in the constructor from the derived classes and can be overridden so users can provide their
	 * own version of a new {@link Component} of the content.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Component} of the content
	 */
	protected Component newContentPanel(final String id)
	{
		final ContentPanel contentPanel = new ContentPanel("contentPanel",
			Model
				.of(ContentModelBean.builder()
					.headerResourceKey(ResourceBundleKey.builder()
						.key("sem.main.info.frame.deregistration.user.label")
						.parameters(ListExtensions.toObjectArray(getDomainName())).build())
				.contentResourceKey(
					ResourceBundleKey.builder().key("sem.main.info.frame.deregistration.user.label")
						.parameters(ListExtensions.toObjectArray(getDomainName())).build())
			.build()));
		contentPanel.getHeader().add(new JQueryJsAppenderBehavior("wrap", "<h1></h1>"));
		contentPanel.getContent()
			.add(new JQueryJsAppenderBehavior("wrap", "<p class=\"lead\"></p>"));
		return contentPanel;

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
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
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
	protected Label newLabel(final String id, final ResourceBundleKey resourceKey)
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
	protected LabeledTextAreaPanel<String, DeregistrationModelBean> newMotivation(final String id,
		final IModel<DeregistrationModelBean> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("sem.main.feedback.deregistration.user.label")
				.defaultValue("Please confirm the deregistration")
				.parameters(ListExtensions.toObjectArray(getDomainName())).build(),
			this);
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.deregistration.motivation.label", this,
			"Enter here your deregistration motivation.");
		final LabeledTextAreaPanel<String, DeregistrationModelBean> description = new LabeledTextAreaPanel<String, DeregistrationModelBean>(
			id, model, labelModel)
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected TextArea<String> newTextArea(final String id,
				final IModel<DeregistrationModelBean> model)
			{
				final TextArea<String> textArea = super.newTextArea(id, model);
				if (placeholderModel != null)
				{
					textArea.add(new AttributeAppender("placeholder", placeholderModel));
				}
				return textArea;
			}
		};
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * Abstract callback method that have to be overwritten to provide specific action for the
	 * deregistration.
	 *
	 * @param target
	 *            the target
	 */
	public abstract void onDeregistration(final AjaxRequestTarget target);

}
