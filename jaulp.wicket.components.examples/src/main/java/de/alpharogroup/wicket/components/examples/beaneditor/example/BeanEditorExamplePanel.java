package de.alpharogroup.wicket.components.examples.beaneditor.example;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.beaneditor.BeanEditorPanel;
import de.alpharogroup.wicket.components.examples.beaneditor.Customer;
import de.alpharogroup.wicket.components.examples.beaneditor.Mandatory;
import de.alpharogroup.wicket.components.labeled.dropdown.LabeledEnumDropDownPanel;

public class BeanEditorExamplePanel extends Panel {

  /**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

public BeanEditorExamplePanel(String id, IModel<Customer> model) {
    super(id, model);

    add(new BeanEditorPanel<Customer>("beanEditorPanel", model) {
      /**
		 * The serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

	@Override
      protected Component newEditorForBeanField(String id, Field field, IModel model) {

        final Class<?> type = field.getType();

        // Check the field if it is @Mandatory.
        boolean required = field.getAnnotation(Mandatory.class) != null;
        IModel<String> labelModel = Model.of(field.getName());
//                new StringResourceModel(field.getName(), this, null);
        if (String.class.isAssignableFrom(type))
        {
            return new StringEditorPanel(id, model, labelModel, required);
        }
        else if (Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type))
        {
            return new BooleanEditorPanel(id, model, labelModel);
        }
        else if (Enum.class.isAssignableFrom(type))
          {
            final List<?> list = Arrays.asList(type.getEnumConstants());
            IModel<?> enumChoices = new AbstractReadOnlyModel<Object>()
            {
              /**
				 * The serialVersionUID
				 */
				private static final long serialVersionUID = 1L;

			@Override
              public Object getObject()
              {
                return list;
              }
            };
            return new LabeledEnumDropDownPanel(id, model, labelModel, enumChoices);
          }
          else
          {
            throw new RuntimeException("Type " + type + " not supported.");
          }
      }

      @Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
          System.out.println(getModel());
      }
    });
  }
}
