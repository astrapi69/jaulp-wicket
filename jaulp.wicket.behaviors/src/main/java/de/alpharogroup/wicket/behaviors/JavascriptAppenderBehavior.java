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

import java.util.UUID;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.util.lang.Args;

/**
 * The Class JavascriptAppenderBehavior simply adds the given javascript code as String with an id in the html page as script block.
 */
public class JavascriptAppenderBehavior extends Behavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The unique id for the javascript element. This can be null, however in that case the ajax
	 * header contribution can't detect duplicate script fragments.
	 */
	private final String id;

	/** The javascript code to be rendered. */
	private final CharSequence javascript;

	/**
	 * Instantiates a new adds the javascript behavior. The id will be generated.
	 *
	 * @param javascript
	 *            javascript content to be add.
	 */
	public JavascriptAppenderBehavior(CharSequence javascript)
	{
		this(javascript, String.valueOf(UUID.randomUUID()));
	}

	/**
	 * Instantiates a new adds the javascript behavior.
	 *
	 * @param javascript
	 *            javascript content to be add.
	 * @param id
	 *            unique id for the javascript element.
	 */
	public JavascriptAppenderBehavior(CharSequence javascript, String id)
	{
		this.javascript = Args.notNull(javascript, "javascript");
		if (id == null)
		{
			id = String.valueOf(UUID.randomUUID());
		}
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		response.render(JavaScriptHeaderItem.forScript(this.javascript, this.id));
	}

}
