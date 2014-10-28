package de.alpharogroup.wicket.components.deregistration;

import net.sourceforge.jaulp.collections.ListUtils;
import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.base.BasePanel;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.i18n.content.ContentPanel;
import de.alpharogroup.wicket.components.labeled.textarea.LabeledTextAreaPanel;

/**
 * The Class DeregistrationPanel.
 *
 * @author Asterios Raptis
 */
public abstract class DeregistrationPanel extends
		BasePanel<DeregistrationModel> {

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
	 */
	public DeregistrationPanel(final String id, final IModel<DeregistrationModel> model) {
		super(id, model);	
		add(contentPanel = newContentPanel("contentPanel"));
		add(form = new Form<DeregistrationModel>("form", model));
		form.add(motivation = newMotivation("motivation", model));
		// Create submit button for the form
		submitButton = newButton("submitButton");			
		submitButton.add(buttonLabel = newButtonLabel("buttonLabel", 
				"sem.main.global.deregistration.user.label",
				"Deregister"));	
		form.add(submitButton);
	}
	
	protected Component newContentPanel(String id) {
		ContentPanel contentPanel = new ContentPanel("contentPanel", null) {
			private static final long serialVersionUID = 1L;
			@Override
			protected ResourceBundleKey newHeaderResourceKey() {
				return ResourceBundleKey.builder()
						.key("sem.main.info.frame.deregistration.user.label")
						.parameters(ListUtils
								.toObjectArray(getDomainName()))
						.build();
			}
			@Override
			protected ResourceBundleKey newContentResourceKey() {
				return ResourceBundleKey.builder()
						.key("sem.main.header.deregistration.user.label")
						.parameters(ListUtils
								.toObjectArray(getDomainName()))
						.build();
			}
		};
		contentPanel.getHeader().add(
				new AddJsQueryBehavior("wrap", "<h1></h1>"));
		contentPanel.getContent().add(
				new AddJsQueryBehavior("wrap", "<p class=\"lead\"></p>"));
		return contentPanel;
		
	}

	/**
	 * Factory method for creating the Label. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users
	 * can provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @return the label
	 */
	protected Label newLabel(String id, final ResourceBundleKey resourceKey) {
		Label label = new Label(id, ResourceModelFactory.newResourceModel(
				resourceKey, this));
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * Factory method for creating the LabeledTextAreaPanel. This method is
	 * invoked in the constructor from the derived classes and can be overridden
	 * so users can provide their own version of a Form.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected LabeledTextAreaPanel<?> newMotivation(String id,
			IModel<DeregistrationModel> model) {
		final IModel<String> labelModel = ResourceModelFactory
				.newResourceModel(ResourceBundleKey.builder()
						.key("sem.main.feedback.deregistration.user.label")
						.defaultValue("Please confirm the deregistration")
						.parameters(ListUtils
								.toObjectArray(getDomainName()))
						.build(), this);
		final IModel<String> placeholderModel = ResourceModelFactory
				.newResourceModel(
						"global.enter.your.deregistration.motivation.label",
						this, "Enter here your deregistration motivation.");
		LabeledTextAreaPanel<DeregistrationModel> description = new LabeledTextAreaPanel<DeregistrationModel>(
				id, model, labelModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected TextArea<DeregistrationModel> newTextArea(String id,
					PropertyModel<DeregistrationModel> model) {
				TextArea<DeregistrationModel> textArea = super.newTextArea(id,
						model);
				if (placeholderModel != null) {
					textArea.add(new AttributeAppender("placeholder",
							placeholderModel));
				}
				return super.newTextArea(id, model);
			}
		};
		return description;
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
	@SuppressWarnings("unchecked")
	protected Form<?> newForm(String id, IModel<?> model) {
		return new Form<DeregistrationModel>(id,
				(IModel<DeregistrationModel>) model);
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
				onDeregistration();
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
		final IModel<String> labelModel = ResourceModelFactory
				.newResourceModel(resourceKey, this, defaultValue);
		Label label = new Label(id, labelModel);
		label.setOutputMarkupId(true);
		return label;
	}

	public Label getButtonLabel() {
		return buttonLabel;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public Component getMotivation() {
		return motivation;
	}

	public Component getContentPanel() {
		return contentPanel;
	}

	public Form<?> getForm() {
		return form;
	}

	public abstract void onDeregistration();

	public abstract String getDomainName();

}
