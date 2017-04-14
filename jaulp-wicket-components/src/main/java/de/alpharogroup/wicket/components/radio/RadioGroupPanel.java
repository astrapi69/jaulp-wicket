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
package de.alpharogroup.wicket.components.radio;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class {@link RadioGroupPanel}.
 *
 * @param <T>
 *            the generic type of model object.
 */
public abstract class RadioGroupPanel<T> extends BasePanel<RadioGroupModelBean<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The group
	 */
	@Getter
	private RadioGroup<T> group;

	/** The form. */
	Form<?> form;

	/**
	 * Instantiates a new {@link RadioGroupPanel}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public RadioGroupPanel(final String id, final IModel<RadioGroupModelBean<T>> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		add(form = newForm("form", model));

		form.add(
			group = newRadioGroup(newRadioName(),
				new PropertyModel<>(model, "selected")
				)
				);

		group.add(newRadioListView("choice", model));
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
	protected Form<RadioGroupModelBean<T>> newForm(final String id,
		final IModel<RadioGroupModelBean<T>> model)
	{
		final Form<RadioGroupModelBean<T>> form = ComponentFactory.newForm(id, model);
		return form;
	}

	/**
	 * Factory method for creating the new {@link Label}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Label newLabel(final String id, final String forId, final IModel<T> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	}

	/**
	 * Factory method for create the new {@link RadioGroup}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link RadioGroup}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link RadioGroup}
	 */
	protected RadioGroup<T> newRadioGroup(final String id, final IModel<T> model)
	{
		final RadioGroup<T> group = ComponentFactory.newRadioGroup(id, model);
		group.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				RadioGroupPanel.this.onUpdate(target);
			}
		});
		return group;
	}

	/**
	 * Factory method for create the new {@link ListView} for the {@link Radio} objects. This method
	 * is invoked in the constructor from the derived classes and can be overridden so users can
	 * provide their own version of a new {@link ListView} for the {@link Radio} objects.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link ListView} for the {@link Radio} objects.
	 */
	protected ListView<T> newRadioListView(final String id, final IModel<RadioGroupModelBean<T>> model)
	{
		final ListView<T> radioListView = new ListView<T>("choice", model.getObject().getRadios())
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(final ListItem<T> item)
			{
				final Radio<T> radio = new Radio<>("radio", item.getModel(),
					RadioGroupPanel.this.group);
				radio.setOutputMarkupId(true);
				item.add(radio);
				item.add(
					RadioGroupPanel.this.newLabel("label", radio.getMarkupId(), item.getModel()));
			}
		};
		radioListView.setOutputMarkupId(true);
		return radioListView;
	};

	/**
	 * Factory method for create a new radio name. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a new
	 * radio name.
	 *
	 * @return the new radio name.
	 */
	protected String newRadioName()
	{
		return "group";
	}

	/**
	 * Callback method that can be overwritten to provide specific action for the update.
	 *
	 * @param target
	 *            the target
	 */
	protected void onUpdate(final AjaxRequestTarget target)
	{
	}

}
