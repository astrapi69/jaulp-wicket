package de.alpharogroup.wicket.components.report;

import net.sourceforge.jaulp.exception.ExceptionUtils;

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
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.component.IRequestablePage;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;


import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;

/**
 * The Class ReportThrowablePanel can present an exception that is thrown from the application.
 */
public abstract class ReportThrowablePanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(ReportThrowablePanel.class.getName());
	
	/** The header. */
	private final Label header;

	/** The form. */
	private final Form<?> form;

	/** The description. */
	private final LabeledTextAreaPanel<ReportThrowableModel> description;

	/** The submit button. */
	private final Button submitButton;

	/** The stack trace. */
	private final Component stackTrace;

	/**
	 * Gets the stack trace.
	 *
	 * @return the stack trace
	 */
	public Component getStackTrace() {
		return stackTrace;
	}

	/** The report model. */
	private final ReportThrowableModel reportThrowableModel;

	/**
	 * Instantiates a new report throwable panel.
	 *
	 * @param id the id
	 * @param throwable the throwable
	 */
	public ReportThrowablePanel(String id, Throwable throwable) {
		super(id);

		reportThrowableModel = newReportThrowableModel(throwable);

		IModel<ReportThrowableModel> cpm = new CompoundPropertyModel<ReportThrowableModel>(
				reportThrowableModel);
		setDefaultModel(cpm);
		add(header = newHeaderLabel("header",
				ResourceModelFactory.newResourceModel("header.label", this,
						"Upps! An error occured.")));

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

	private static class Effects {

		private static void replace(AjaxRequestTarget target, Component component) {
			component.add(new DisplayNoneBehavior());

//			target.prependJavaScript("notify|jQuery('#"+component.getMarkupId()+"').slideUp(1000, notify);");
			target.add(component);
			target.appendJavaScript("jQuery('#"+component.getMarkupId()+"').slideDown(100);");
		}
	}
	private static class DisplayNoneBehavior extends AttributeModifier {
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
	 * @param id the id
	 * @return the component
	 */
	protected Component newHiddenField(String id) {
		HiddenField<String> hiddenField = new HiddenField<String>(id);
		return hiddenField;
	}

	/**
	 * New header label.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the label
	 */
	protected Label newHeaderLabel(String id, IModel<String> model) {
		Label label = new Label(id, model);
		return label;
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
		return new Form<Void>(id);
	}

	/**
	 * New submit button.
	 *
	 * @param id the id
	 * @return the button
	 */
	protected Button newSubmitButton(String id) {
		return new Button(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				/**
				 * Do your stuff here (i.e. Send Email)
				 */
				onSubmitError();
			}
		};
	}

	/**
	 * Hook method for submitting the error when the submit button is clicked.
	 * Implement here everything what to do when the user submits the form.
	 */
	protected abstract void onSubmitError();

	/**
	 * New description.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the labeled text area panel
	 */
	protected LabeledTextAreaPanel<ReportThrowableModel> newDescription(
			String id, IModel<ReportThrowableModel> model) {
		final IModel<String> labelModel = ResourceModelFactory
				.newResourceModel("description.label", this,
						"Please provide here any useful information");
		final IModel<String> placeholderModel = ResourceModelFactory
				.newResourceModel("global.enter.your.description.label", this,
						"Enter here any useful information");
		LabeledTextAreaPanel<ReportThrowableModel> description = new LabeledTextAreaPanel<ReportThrowableModel>(
				id, model, labelModel){
					private static final long serialVersionUID = 1L;
			@Override
			protected TextArea<ReportThrowableModel> newTextArea(
					String id, PropertyModel<ReportThrowableModel> model) {
				TextArea<ReportThrowableModel> textArea = super.newTextArea(id, model);
				if(placeholderModel != null) {
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
	 * @param throwable the throwable
	 * @return the report throwable model
	 */
	protected ReportThrowableModel newReportThrowableModel(Throwable throwable) {
		return ReportThrowableModel.builder()
				.affectedUsername(newAffectedUsername())
				.responsePage(newResponsePageClass())
				.rootUsername(newRootUsername())
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
	 * @return the class<? extends i requestable page>
	 */
	protected abstract Class<? extends IRequestablePage> newResponsePageClass();

	/**
	 * New affected username.
	 *
	 * @return the string
	 */
	protected abstract String newAffectedUsername();

	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public Label getHeader() {
		return header;
	}

	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	public Form<?> getForm() {
		return form;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public LabeledTextAreaPanel<ReportThrowableModel> getDescription() {
		return description;
	}

	/**
	 * Gets the submit button.
	 *
	 * @return the submit button
	 */
	public Button getSubmitButton() {
		return submitButton;
	}

}
