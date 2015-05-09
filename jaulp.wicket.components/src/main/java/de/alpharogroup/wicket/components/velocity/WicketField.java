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
package de.alpharogroup.wicket.components.velocity;

import org.apache.wicket.Component;

import de.alpharogroup.xml.tag.SimpleTag;

/**
 * The Class WicketField encapsulates a wicket component and can create its own html tag for this
 * component. It can be used with velocity to create dynamic panel without create html templates for
 * it.
 * 
 * @param <C>
 *            the generic type for the Component
 */
public class WicketField<C extends Component> extends SimpleTag
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant WICKET_ID for the corresponding html attribute. */
	public static final String WICKET_ID = "wicket:id";

	/** The component. */
	private C component;

	/**
	 * Gets the component.
	 * 
	 * @return the component
	 */
	public C getComponent()
	{
		return component;
	}

	/**
	 * Gets the wicket id.
	 * 
	 * @return the wicket id
	 */
	public String getWicketId()
	{
		return getAttributes().get(WICKET_ID);
	}

	/**
	 * Sets the component.
	 * 
	 * @param component
	 *            the new component
	 */
	public void setComponent(C component)
	{
		this.component = component;
	}

}
