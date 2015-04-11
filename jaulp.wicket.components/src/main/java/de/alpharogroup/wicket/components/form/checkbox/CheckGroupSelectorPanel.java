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
package de.alpharogroup.wicket.components.form.checkbox;

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.util.Collection;

import lombok.Getter;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class CheckGroupSelectorPanel<T> extends BasePanel<CheckboxModel<T>>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the form.
	 */
	@Getter
	private final Form<?> form;

	@Getter
	private final CheckGroup<T> checkGroup;

	@Getter
	private final CheckGroupSelector checkGroupSelector;

	@Getter
	private final Label checkGroupSelectorLabel;

	@Getter
	private final ListView<T> choices;

	public CheckGroupSelectorPanel(String id, IModel<CheckboxModel<T>> model)
	{
		super(id, model);

		add(form = newForm("form"));

		form.add(checkGroup = newCheckGroup("checkGroup", model(from(model).getSelectedItems())));

		checkGroup
			.add(checkGroupSelector = newCheckGroupSelector("checkGroupSelector", checkGroup));

		checkGroup.add(checkGroupSelectorLabel = newCheckGroupSelectorLabel(
			"checkGroupSelectorLabel", checkGroupSelector.getMarkupId(),
			Model.of("check/uncheck all")));

		checkGroup.add(choices = newChoices("choices", model));
	}

	protected ListView<T> newChoices(final String id, final IModel<CheckboxModel<T>> model)
	{
		ListView<T> choices = new ListView<T>("choices", model.getObject().getChoices())
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<T> item)
			{
				item.add(new Check<>("checkbox", item.getModel()));
				item.add(new Label("label", new PropertyModel<String>(item.getDefaultModel(), model
					.getObject().getLabelPropertyExpression())));
			}
		};
		choices.setReuseItems(true);
		return choices;
	}

	protected Label newCheckGroupSelectorLabel(final String id, final String forId,
		final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	}

	/**
	 * Factory method for create a new {@link CheckGroupSelector}.
	 *
	 * @param id
	 *            the id
	 * @param group
	 *            the {@link CheckGroup}
	 * @return the new {@link CheckGroupSelector}
	 */
	protected CheckGroupSelector newCheckGroupSelector(final String id, final CheckGroup<T> group)
	{
		return ComponentFactory.newCheckGroupSelector(id, group);
	}

	/**
	 * New form.
	 *
	 * @param id
	 *            the id
	 * @return the form
	 */
	protected Form<?> newForm(final String id)
	{
		return ComponentFactory.newForm(id);
	}

	/**
	 * New check group.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the radio group
	 */
	protected CheckGroup<T> newCheckGroup(final String id,
		final IModel<? extends Collection<T>> model)
	{
		CheckGroup<T> checkGroup = ComponentFactory.newCheckGroup(id, model);
		checkGroup.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				CheckGroupSelectorPanel.this.onUpdate(target);
			}
		});
		return checkGroup;
	}

	protected void onUpdate(AjaxRequestTarget target)
	{
	}
}
