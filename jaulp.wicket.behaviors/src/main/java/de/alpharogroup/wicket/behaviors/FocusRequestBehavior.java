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
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;

/**
 * The Class FocusRequestBehavior set the focus on a component when the page is load.
 */
public class FocusRequestBehavior extends Behavior
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 2312277970691939826L;
	/** The Constant DEFAULT_ID is the default id that will be set if the id is not set explicit. */
	public static final String DEFAULT_ID = FocusRequestBehavior.class.getSimpleName();

	/** The flag if the value may be clear. */
	private boolean clearValue;

	/**
	 * Instantiates a new request focus behavior.
	 */
	public FocusRequestBehavior()
	{
		this(false);
	}

	/**
	 * Instantiates a new focus request behavior.
	 *
	 * @param clearValue
	 *            The flag if the value may be clear.
	 */
	public FocusRequestBehavior(boolean clearValue)
	{
		this.clearValue = clearValue;
	}

	/**
	 * Creates the java script code for request focus.
	 *
	 * @param component
	 *            the component
	 * @return the string
	 */
	private String createJavaScript(Component component)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("setTimeout(" + "function() {" + "var component = document.getElementById(\"")
			.append(component.getMarkupId()).append("\");");
		if (clearValue)
		{
			sb.append("component.value = \"\";");
		}
		sb.append("component.focus();");
		sb.append("component.select();");
		sb.append("}, 1)");
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		component.setOutputMarkupId(true);
		response.render(OnLoadHeaderItem.forScript(createJavaScript(component)));
		super.renderHead(component, response);
	}
}
