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
package de.alpharogroup.wicket.behaviors.models;

import java.io.Serializable;
import java.util.Objects;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.ComponentFinder;

/**
 * The class {@link ModelUpdateBehavior}.
 *
 * @param <T>
 *            the generic type of the model
 */
public class ModelUpdateBehavior<T extends Serializable> extends Behavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Factory method to create a new {@link ModelUpdateBehavior} object.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param model
	 *            the model
	 * @return the new {@link ModelUpdateBehavior} object
	 */
	public static <T extends Serializable> ModelUpdateBehavior<T> of(final IModel<T> model)
	{
		return new ModelUpdateBehavior<>(model);
	}

	/** The model. */
	private IModel<T> model;

	/** The model object. */
	private T previousModelObject;

	/**
	 * Instantiates a new {@link ModelUpdateBehavior}.
	 *
	 * @param model
	 *            the model
	 */
	public ModelUpdateBehavior(final IModel<T> model)
	{
		this.model = Args.notNull(model, "model");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(final Component component)
	{
		super.bind(component);
		component.setOutputMarkupPlaceholderTag(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onConfigure(final Component component)
	{
		super.onConfigure(component);
		this.previousModelObject = this.model.getObject();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEvent(final Component component, final IEvent<?> event)
	{
		super.onEvent(component, event);
		final T currentModelObject = this.model.getObject();
		if (!Objects.equals(currentModelObject, this.previousModelObject))
		{
			this.previousModelObject = currentModelObject;
			component.modelChanging();
			component.modelChanged();
			final AjaxRequestTarget ajaxRequestTarget = ComponentFinder.findAjaxRequestTarget();
			if (ajaxRequestTarget != null)
			{
				ajaxRequestTarget.add(component);
			}
		}
	}

}
