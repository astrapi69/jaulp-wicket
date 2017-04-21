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

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;
import lombok.Getter;

/**
 * The Class ReportThrowablePanel can present an exception that is thrown from the application.
 */
public abstract class ReportThrowablePanel extends BasePanel<ReportThrowableModelBean>
{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant logger. */
	private static final Logger LOGGER = Logger.getLogger(ReportThrowablePanel.class.getName());

	/** The header. */
	@Getter
	private Label header;

	/** The form. */
	@Getter
	private Form<?> form;

	/** The description. */
	@Getter
	private LabeledTextAreaPanel<String, ReportThrowableModelBean> description;


	/** The submit button. */
	@Getter
	private Button submitButton;

	/** The stack trace. */
	@Getter
	private HiddenField<String> stackTrace;

	/** The {@link MultiLineLabel} label for the expanded stack trace. */
	@Getter
	private MultiLineLabel toReplace;

	/** The report model. */
	private ReportThrowableModelBean reportThrowableModel;

	/**
	 * Instantiates a new {@link ReportThrowablePanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public ReportThrowablePanel(final String id, final IModel<ReportThrowableModelBean> model)
	{
		super(id, model);
		reportThrowableModel = model.getObject();
	}

	/**
	 * Factory method for create the new {@link LabeledTextAreaPanel}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link LabeledTextAreaPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link LabeledTextAreaPanel}
	 */
	protected LabeledTextAreaPanel<String, ReportThrowableModelBean> newDescription(final String id,
		final IModel<ReportThrowableModelBean> model)
	{
		final IModel<String> labelModel = ResourceModelFactory.newResourceModel("description.label",
			this, "Please provide here any useful information");
		final IModel<String> placeholderModel = ResourceModelFactory.newResourceModel(
			"global.enter.your.description.label", this, "Enter here any useful information");
		final LabeledTextAreaPanel<String, ReportThrowableModelBean> description = new LabeledTextAreaPanel<String, ReportThrowableModelBean>(
			id, model, labelModel)
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected TextArea<String> newTextArea(final String id,
				final IModel<ReportThrowableModelBean> model)
			{
				final TextArea<String> textArea = super.newTextArea(id, model);
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
	 * Factory method for create the new {@link Form}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Form}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Form}
	 */
	protected Form<?> newForm(final String id, final IModel<?> model)
	{
		return ComponentFactory.newForm(id, model);
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Label newHeaderLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Factory method for creating the new {@link HiddenField}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link HiddenField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link HiddenField}
	 */
	protected HiddenField<String> newHiddenField(final String id, final IModel<String> model)
	{
		return ComponentFactory.newHiddenField(id, model);
	}

	/**
	 * Factory method for creating the new {@link Button}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Button}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link Button}
	 */
	protected Button newSubmitButton(final String id)
	{
		return new Button(id)
		{
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onSubmit()
			{
				/**
				 * Here comes the action what to do like (i.e. Send Email)
				 */
				onSubmitError(null);
			}
		};
	}


	protected MultiLineLabel newToReplace(final String id, final IModel<String> model)
	{
		final MultiLineLabel multiLineLabel = ComponentFactory.newMultiLineLabel(id, model);
		return multiLineLabel;
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(header = newHeaderLabel("header", ResourceModelFactory.newResourceModel("header.label",
			this, "Upps! An error occured.")));

		add(form = newForm("form", getModel()));

		form.add(description = newDescription("description", getModel()));

		form.add(toReplace = newToReplace("toReplace", Model.of("")));

		final AjaxLink<Void> link = new AjaxLink<Void>("link")
		{

			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				toReplace.setDefaultModelObject(reportThrowableModel.getStackTrace());
				Effects.replace(target, toReplace);

			}
		};

		form.add(link);

		form.add(submitButton = newSubmitButton("submitButton"));

		form.add(stackTrace = newHiddenField("stackTrace",
			PropertyModel.<String> of(getModel(), "stackTrace")));
		LOGGER.error(reportThrowableModel.getStackTrace());
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onSubmitError(final AjaxRequestTarget target);

}
