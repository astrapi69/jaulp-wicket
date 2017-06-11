package de.alpharogroup.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;

/**
 * The abstract class {@link ComponentDecoratorBehavior} provides callback
 * methods for decorate(wrap) a component with html tags. There for you can get
 * the {@link Response} of the component and write to it.
 */
public abstract class ComponentDecoratorBehavior extends Behavior {

	/** The constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(Component component) {
		component.setOutputMarkupId(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeRender(Component component) {
		onBeforeRender(component);
		super.beforeRender(component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterRender(Component component) {
		super.afterRender(component);
		onAfterRender(component);
	}

	/**
	 * Factory callback method to hook after render.
	 *
	 * @param component
	 *            the component
	 */
	protected void onAfterRender(Component component) {
		Response response = component.getResponse();
		response.write(onWriteAfterRender());
	}

	/**
	 * Factory callback method to hook before render.
	 *
	 * @param component
	 *            the component
	 */
	protected void onBeforeRender(Component component) {
		Response response = component.getResponse();
		response.write(onWriteBeforeRender());
	}

	/**
	 * Abstract factory callback method that have to be overwritten. The
	 * returned result will be written in the response before the rendering
	 * process starts.
	 *
	 * @return the {@link String} that will be written in the response before
	 *         the rendering process starts.
	 */
	protected abstract String onWriteBeforeRender();

	/**
	 * Abstract factory callback method that have to be overwritten. The
	 * returned result will be written in the response after the rendering
	 * process finishes.
	 *
	 * @return the {@link String} that will be written in the response after the
	 *         rendering process finishes.
	 */
	protected abstract String onWriteAfterRender();
    
}
