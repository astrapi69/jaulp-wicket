package org.jaulp.wicket.dialogs.panels.save;

import lombok.Getter;
import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class SaveDialogPanel.
 *
 * @param <T> the generic type
 */
public class SaveDialogPanel<T> extends BasePanel<T> {

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/**
	 * Gets the description label.
	 *
	 * @return the description label
	 */
	@Getter
	private final Label descriptionLabel;
	
	/**
	 * Gets the cancel label.
	 *
	 * @return the cancel label
	 */
	@Getter
	private final Label cancelLabel;
	
	/**
	 * Gets the save label.
	 *
	 * @return the save label
	 */
	@Getter
	private final Label saveLabel;

	/**
	 * Gets the cancel button.
	 *
	 * @return the cancel button
	 */
	@Getter
	private final AjaxButton cancelButton;	

	/**
	 * Gets the save button.
	 *
	 * @return the save button
	 */
	@Getter
	private final AjaxButton saveButton;
	
	/** The label text field. */
	@Getter
	private Component textFieldLabel;
	
	/**
	 * Gets the form.
	 *
	 * @return the form
	 */
	@Getter
	private Form<?> form;		
	/** The text field. */
	@Getter
	private Component textField;

	/**
	 * Instantiates a new save dialog panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public SaveDialogPanel(String id, IModel<T> model) {
		super(id, model);
		setOutputMarkupId(true);
		add(form = newForm("form", model));
		form.add(descriptionLabel = newDescriptionLabel("descriptionLabel", 
				ResourceBundleKey.builder()
				.key("save.description.label")
				.build()));	
		form.add(textFieldLabel = newTextFieldLabel("textFieldLabel", ResourceBundleKey.builder()
						.key("main.global.form.textfield.label")
						.build()));
		form.add(textField = newTextField("textField", model));
		
		form.add(cancelButton = newCancelButton("cancelButton", form));
		cancelButton.add(cancelLabel = newCancelLabel("cancelLabel", 
				ResourceBundleKey.builder()
				.key("main.global.cancel.button.label")
				.defaultValue("Cancel").build()));
		form.add(saveButton = newSaveButton("saveButton", form));
		saveButton.add(saveLabel = newSaveLabel("saveLabel", 
				ResourceBundleKey.builder()
				.key("main.global.save.button.label")
				.defaultValue("Save").build()));
	}
	
	protected Component newTextFieldLabel(String id, final ResourceBundleKey resourceKey) {
		return ComponentFactory.newLabel(id, resourceKey, this);
	}
	
	protected Component newTextField(String id, IModel<T> model) {
		return ComponentFactory.newTextField(id, model);
	}
	
	/**
	 * Factory method for creating a new {@link Form}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a {@link Form}.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the Form
	 */
	protected Form<?> newForm(String id, IModel<T> model) {
		return ComponentFactory.newForm(id, model);
	}
	
	/**
	 * Factory method for creating a new save {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a save {@link AjaxButton}.
	 *
	 * @param id the id
	 * @param form the form
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newSaveButton(String id, Form<?> form){
		AjaxButton saveButton = new AjaxButton(id, form) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {
				onSave(target, form);
			}
		};
		return saveButton;		
	}
	
	/**
	 * Factory method for creating a new cancel {@link AjaxButton}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a cancel {@link AjaxButton}.
	 *
	 * @param id the id
	 * @param form the form
	 * @return the new {@link AjaxButton}
	 */
	protected AjaxButton newCancelButton(String id, Form<?> form){
		AjaxButton cancelButton = new AjaxButton(id, form) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {
				onCancel(target, form);
			}
		};
		return cancelButton;		
	}
	
	/**
	 * Factory method for creating a new description {@link Label} with a {@link ResourceBundleKey}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a description {@link Label}.
	 *
	 * @param id            the id
	 * @param resourceKey            the resource key
	 * @return the new {@link Label}
	 */
	protected Label newDescriptionLabel(String id, final ResourceBundleKey resourceKey) {
		return ComponentFactory.newLabel(id, resourceKey, this);
	}
	
	/**
	 * Factory method for creating a new save {@link Label} with a {@link ResourceBundleKey}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a save {@link Label}.
	 *
	 * @param id            the id
	 * @param resourceKey            the resource key
	 * @return the new {@link Label}
	 */
	protected Label newSaveLabel(String id, final ResourceBundleKey resourceKey) {
		return ComponentFactory.newLabel(id, resourceKey, this);
	}
	
	/**
	 * Factory method for creating a new cancel {@link Label} with a {@link ResourceBundleKey}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a cancel {@link Label}.
	 *
	 * @param id            the id
	 * @param resourceKey            the resource key
	 * @return the new {@link Label}
	 */
	protected Label newCancelLabel(String id, final ResourceBundleKey resourceKey) {
		return ComponentFactory.newLabel(id, resourceKey, this);
	}
	
	/**
	 * Callback method for cancel.
	 *
	 * @param target the target
	 * @param form the form
	 */
	protected void onCancel(final AjaxRequestTarget target, final Form<?> form) {	
		target.add(form);
	}
	
	/**
	 * Callback method for save.
	 *
	 * @param target the target
	 * @param form the form
	 */
	protected void onSave(final AjaxRequestTarget target, final Form<?> form) {		
		target.add(form);	
	}

}