package de.alpharogroup.wicket.components.examples.beaneditor;

import java.lang.reflect.Field;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * The Class BeanEditorPanel.
 *
 * @param <T> the generic type
 */
public abstract class BeanEditorPanel<T> extends GenericPanel<T> {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new bean editor panel.
   *
   * @param id the id
   * @param model the model
   */
  public BeanEditorPanel(String id, IModel<T> model) {
    super(id, model);
    Form<?> form = new Form<>("form");
    add(form);

    RepeatingView fields = new RepeatingView("fields");
    form.add(fields);

    final T modelObject = model.getObject();
    for (Field field : modelObject.getClass().getDeclaredFields()) {
      // skip serialVersionUID...
      if(field.getName().equalsIgnoreCase("serialVersionUID")) {
        continue;
      }
      WebMarkupContainer row = new WebMarkupContainer(fields.newChildId());
      fields.add(row);

      IModel<String> labelModel = Model.of(field.getName());
      
      row.add(new Label("name", labelModel));
      // TODO change with wicketeer...
      IModel<?> fieldModel = new PropertyModel<Object>(modelObject, field.getName());

      // Create the editor for the field.
      row.add(newEditorForBeanField("editor", field, fieldModel));

    }
    Button button = new AjaxFallbackButton("button", form) {
      /**
		 * The serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

	@Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        BeanEditorPanel.this.onSubmit(target, form);
      }
    };
    form.add(button);
  }
  /**
   * Override this method to provide custom editors for your fields.
   * @param field Java Field in the bean you provided (call getType to retrieve
   * the class of the field).
   * @param model IModel that wraps the actual bean's field.
   * @return
   */

  /**
   * Factory method for an editor for the given field.
   *
   * @param id the id
   * @param field the field
   * @param model the model
   * @return the component
   */
  protected abstract Component newEditorForBeanField(final String id, final Field field, final IModel<?> model);



  /**
   * Hook method for the submit.
   *
   * @param target
   *            the target
   * @param form
   *            the form
   */
  protected void onSubmit(final AjaxRequestTarget target, final Form<?> form){

  }
}
