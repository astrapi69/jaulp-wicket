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


import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;
import lombok.Getter;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

public abstract class RadioGroupPanel<T> extends BasePanel<RadioGroupModel<T>>
{
	private static final long serialVersionUID = 1L;
	@Getter
	private RadioGroup<T> group;

	Form<?> form;

	public RadioGroupPanel(String id, final IModel<RadioGroupModel<T>> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		add(form = newForm("form", model));

		form.add(group = newRadioGroup(newRadioName(), model(from(model.getObject()).getSelected())));

		group.add(newRadioListView("choice", model));
	}

	protected ListView<T> newRadioListView(final String id, final IModel<RadioGroupModel<T>> model)
	{
		ListView<T> radioListView = new ListView<T>("choice", model.getObject().getRadios())
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<T> item)
			{
				Radio<T> radio = new Radio<>("radio", item.getModel(), RadioGroupPanel.this.group);
				radio.setOutputMarkupId(true);
				item.add(radio);
				item.add(RadioGroupPanel.this.newLabel("label", radio.getMarkupId(),
					item.getModel()));
			}
		};
		radioListView.setOutputMarkupId(true);
		return radioListView;
	}

	/**
	 * Factory method for create a new Form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	protected Form<RadioGroupModel<T>> newForm(final String id,
		final IModel<RadioGroupModel<T>> model)
	{
		Form<RadioGroupModel<T>> form = ComponentFactory.newForm(id, model);
		return form;
	}

	/**
	 * Factory method for create a new {@link RadioGroup}.
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
			protected void onUpdate(AjaxRequestTarget target)
			{
				RadioGroupPanel.this.onUpdate(target);
			}
		});
		return group;
	}

	/**
	 * Factory method for creating a new Label with the for attribute.
	 *
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the list item model
	 * @return the label
	 */
	protected Label newLabel(String id, String forId, IModel<T> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	};

	protected void onUpdate(AjaxRequestTarget target)
	{
	}

	protected String newRadioName()
	{
		return "group";
	}

}
