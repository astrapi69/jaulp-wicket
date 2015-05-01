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
package de.alpharogroup.wicket.components.report;

import lombok.Getter;
import de.alpharogroup.exception.ExceptionUtils;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.util.lang.Args;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;

/**
 * The Class ReportThrowablePanel can present an exception that is thrown from the application.
 */
public abstract class ReportThrowablePanel extends GenericPanel<Throwable>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(ReportThrowablePanel.class.getName());

	/** The header. */
	@Getter
	private final Label header;

	/** The form. */
	@Getter
	private final Form<?> form;

	/** The description. */
	@Getter
	private final LabeledTextAreaPanel<ReportThrowableModel> description;

	/** The submit button. */
	@Getter
	private final Button submitButton;

	/** The stack trace. */
	@Getter
	private final Component stackTrace;


	/** The report model. */
	private final ReportThrowableModel reportThrowableModel;

	/**
	 * Instantiates a new report throwable panel.
	 *
	 * @param id
	 *            the id
	 * @param throwable
	 *            the throwable
	 */
	public ReportThrowablePanel(String id, Throwable throwable)
	{
		super(id, Model.of(Args.notNull(throwable, "throwable")));

		reportThrowableModel = newReportThrowableModel(getModelObject());

		IModel<ReportThrowableModel> cpm = new CompoundPropertyModel<>(reportThrowableModel);
		setDefaultModel(cpm);
		add(header = newHeaderLabel("header",
			ResourceModelFactory.newResourceModel("header.label", this, "Upps! An error occured.")));

		add(form = newForm("form", cpm));

		form.add(description = newDescription("description", cpm));

		final MultiLineLabel toReplace = new MultiLineLabel("toReplace", Model.of(""));
		toReplace.setOutputMarkupId(true);
		form.add(toReplace);
		AjaxLink<Void> link = new AjaxLink<Void>("link")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				toReplace.setDefaultModelObject(reportThrowableModel.getStackTrace());
				Effects.replace(target, toReplace);

			}
		};
		form.add(link);

		form.add(submitButton = newSubmitButton("submitButton"));

		form.add(stackTrace = newHiddenField("stackTrace"));
		LOGGER.error(reportThrowableModel.getStackTrace());
	}

	private static class Effects
	{

		private static void replace(AjaxRequestTarget target, Component component)
		{
			component.add(new DisplayNoneBehavior());

			// target.prependJavaScript("notify|jQuery('#"+component.getMarkupId()+"').slideUp(1000, notify);");
			target.add(component);
			target.appendJavaScript("jQuery('#" + component.getMarkupId() + "').slideDown(100);");
		}
	}
	private static class DisplayNoneBehavior extends AttributeModifier
	{
		private static final long serialVersionUID = 1L;

		private DisplayNoneBehavior()
		{
			super("style", Model.of("display: none"));
		}

		@Override
		public boolean isTemporary(Component component)
		{
			return true;
		}
	}

	/**
	 * New hidden field.
	 *
	 * @param id
	 *            the id
	 * @return the component
	 */
	protected Component newHiddenField(String id)
	{
		return ComponentFactory.newHiddenField(id);
	}

	/**
	 * New header label.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newHeaderLabel(String id, IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
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
	 * New submit button.
	 *
	 * @param id
	 *            the id
	 * @return the button
	 */
	protected Button newSubmitButton(String id)
	{
		return new Button(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				/**
				 * Do your stuff here (i.e. Send Email)
				 */
				onSubmitError();
			}
		};
	}

	/**
	 * Hook method for submitting the error when the submit button is clicked. Implement here
	 * everything what to do when the user submits the form.
	 */
	protected abstract void onSubmitError();

	/**
	 * New description.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the labeled text area panel
	 */
	protected LabeledTextAreaPanel<ReportThrowableModel> newDescription(String id,
		IModel<ReportThrowableModel> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel(
			"description.label", this, "Please provide here any useful information");
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.description.label", this, "Enter here any useful information");
		LabeledTextAreaPanel<ReportThrowableModel> description = new LabeledTextAreaPanel<ReportThrowableModel>(
			id, model, labelModel)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected TextArea<ReportThrowableModel> newTextArea(String id,
				IModel<ReportThrowableModel> model)
			{
				TextArea<ReportThrowableModel> textArea = super.newTextArea(id, model);
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
	 * New report throwable model.
	 *
	 * @param throwable
	 *            the throwable
	 * @return the report throwable model
	 */
	protected ReportThrowableModel newReportThrowableModel(Throwable throwable)
	{
		return ReportThrowableModel.builder().affectedUsername(newAffectedUsername())
			.responsePage(newResponsePageClass()).rootUsername(newRootUsername())
			.stackTrace(ExceptionUtils.getStackTraceElements(throwable)).build();
	}

	/**
	 * New root username.
	 *
	 * @return the string
	 */
	protected abstract String newRootUsername();

	/**
	 * New response page class.
	 *
	 * @return the class of the response page
	 */
	protected abstract Class<? extends IRequestablePage> newResponsePageClass();

	/**
	 * New affected username.
	 *
	 * @return the string
	 */
	protected abstract String newAffectedUsername();

}
