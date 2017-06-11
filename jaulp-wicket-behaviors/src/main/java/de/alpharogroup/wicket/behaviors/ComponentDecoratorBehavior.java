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
package de.alpharogroup.wicket.behaviors;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.request.Response;

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
	public void bind(final Component component) {
		component.setOutputMarkupId(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeRender(final Component component) {
		onBeforeRender(component);
		super.beforeRender(component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterRender(final Component component) {
		super.afterRender(component);
		onAfterRender(component);
	}

	/**
	 * Factory callback method to hook after render.
	 *
	 * @param component
	 *            the component
	 */
	protected void onAfterRender(final Component component) {
		final Response response = component.getResponse();
		response.write(onWriteAfterRender());
	}

	/**
	 * Factory callback method to hook before render.
	 *
	 * @param component
	 *            the component
	 */
	protected void onBeforeRender(final Component component) {
		final Response response = component.getResponse();
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
