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
 * The Class {@link ModelUpdateBehavior}.
 *
 * @param <T>
 *            the generic type of the model
 */
public class ModelUpdateBehavior<T extends Serializable> extends Behavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The model. */
	private IModel<T> model;

	/** The model object. */
	private T modelObject;

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
		this.modelObject = this.model.getObject();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEvent(final Component component, final IEvent<?> event)
	{
		super.onEvent(component, event);
		final AjaxRequestTarget ajaxRequestTarget = ComponentFinder.findAjaxRequestTarget();
		final T currentModelObject = this.model.getObject();
		if (!Objects.equals(currentModelObject, this.modelObject))
		{
			this.modelObject = currentModelObject;
			if (ajaxRequestTarget != null)
			{
				component.modelChanging();
				component.modelChanged();
				ajaxRequestTarget.add(component);
			}
		}
	}

}
