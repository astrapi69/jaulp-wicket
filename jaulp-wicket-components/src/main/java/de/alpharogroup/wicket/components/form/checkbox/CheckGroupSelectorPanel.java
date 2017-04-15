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

import java.util.Collection;

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

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class {@link CheckGroupSelectorPanel}.
 *
 * @param <T>
 *            the generic type
 */
public class CheckGroupSelectorPanel<T> extends BasePanel<CheckboxModelBean<T>>
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

	/**
	 * the check group
	 */
	@Getter
	private final CheckGroup<T> checkGroup;

	/**
	 * the check group selector
	 */
	@Getter
	private final CheckGroupSelector checkGroupSelector;

	/**
	 * the check group selector label
	 */
	@Getter
	private final Label checkGroupSelectorLabel;

	/**
	 * the choices
	 */
	@Getter
	private final ListView<T> choices;

	/**
	 * Instantiates a new {@link CheckGroupSelectorPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public CheckGroupSelectorPanel(final String id, final IModel<CheckboxModelBean<T>> model)
	{
		super(id, model);

		add(form = newForm("form"));

		form.add(
			checkGroup = newCheckGroup("checkGroup", new PropertyModel<>(model, "selectedItems")));

		checkGroup
			.add(checkGroupSelector = newCheckGroupSelector("checkGroupSelector", checkGroup));

		checkGroup
			.add(checkGroupSelectorLabel = newCheckGroupSelectorLabel("checkGroupSelectorLabel",
				checkGroupSelector.getMarkupId(), Model.of("check/uncheck all")));

		checkGroup.add(choices = newChoices("choices", model));
	}

	/**
	 * Factory method for create a new {@link CheckGroup}. This method is invoked in the constructor
	 * from the derived classes and can be overridden so users can provide their own version of a
	 * new {@link CheckGroup}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link CheckGroup}
	 */
	protected CheckGroup<T> newCheckGroup(final String id,
		final IModel<? extends Collection<T>> model)
	{
		final CheckGroup<T> checkGroup = ComponentFactory.newCheckGroup(id, model);
		checkGroup.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				CheckGroupSelectorPanel.this.onUpdate(target);
			}
		});
		return checkGroup;
	}

	/**
	 * Factory method for create a new {@link CheckGroupSelector}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link CheckGroupSelector}.
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
	protected Label newCheckGroupSelectorLabel(final String id, final String forId,
		final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, forId, model);
	}

	/**
	 * Factory method for creating the new {@link ListView} for the choices. This method is invoked
	 * in the constructor from the derived classes and can be overridden so users can provide their
	 * own version of the new {@link ListView} for the choices.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link ListView} for the choices.
	 */
	protected ListView<T> newChoices(final String id, final IModel<CheckboxModelBean<T>> model)
	{
		final ListView<T> choices = new ListView<T>("choices", model.getObject().getChoices())
		{
			/** The serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void populateItem(final ListItem<T> item)
			{
				item.add(new Check<>("checkbox", item.getModel()));
				item.add(new Label("label", new PropertyModel<String>(item.getDefaultModel(),
					model.getObject().getLabelPropertyExpression())));
			}
		};
		choices.setReuseItems(true);
		return choices;
	}

	/**
	 * Factory method for creating the Form. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Form.
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
	 * Callback method that can be overwritten to provide specific action for the update.
	 *
	 * @param target
	 *            the target
	 */
	protected void onUpdate(final AjaxRequestTarget target)
	{
	}
}
