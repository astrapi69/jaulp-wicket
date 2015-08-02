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

import de.alpharogroup.test.objects.Customer;
import de.alpharogroup.test.objects.annotations.Mandatory;
import de.alpharogroup.wicket.components.beaneditor.BeanEditorPanel;
import de.alpharogroup.wicket.components.labeled.dropdown.LabeledEnumDropDownPanel;

public class BeanEditorExamplePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public BeanEditorExamplePanel(final String id, final IModel<Customer> model)
	{
		super(id, model);

		add(new BeanEditorPanel<Customer>("beanEditorPanel", model)
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			protected Component newEditorForBeanField(final String id, final Field field,
				final IModel model)
			{

				final Class<?> type = field.getType();

				// Check the field if it is @Mandatory.
				final boolean required = field.getAnnotation(Mandatory.class) != null;
				final IModel<String> labelModel = Model.of(field.getName());
				// new StringResourceModel(field.getName(), this, null);
				if (String.class.isAssignableFrom(type))
				{
					return new StringEditorPanel(id, model, labelModel, required);
				}
				else if (Boolean.class.isAssignableFrom(type)
					|| boolean.class.isAssignableFrom(type))
				{
					return new BooleanEditorPanel(id, model, labelModel);
				}
				else if (Enum.class.isAssignableFrom(type))
				{
					final List<?> list = Arrays.asList(type.getEnumConstants());
					final IModel<?> enumChoices = new AbstractReadOnlyModel<Object>()
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
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				System.out.println(getModel());
			}
		});
	}
}
