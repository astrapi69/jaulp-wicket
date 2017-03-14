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

import lombok.Setter;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.head.OnEventHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.util.lang.Args;

/**
 * The Class JavascriptAppenderBehavior simply adds the given javascript code as String with an id
 * in the html page as script block.
 */
public class JavascriptAppenderBehavior extends Behavior
{
	
	/**
	 * The enum {@link JavascriptBindEvent} describes when the js will be bind.
	 */
	public enum JavascriptBindEvent { ONDOMREADY, ONEVENT, ONLOAD};
	
	/** The default bind event. */
	private JavascriptBindEvent DEFAULT_BIND_EVENT = JavascriptBindEvent.ONDOMREADY;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The target of the event handler. default window. */
	@Setter
	private String target="window";
	
	/** The event. default click. */
	@Setter
	private String event = "click";
	
	/** The bind event for the javascript. */
	@Setter
	private JavascriptBindEvent bindEvent = DEFAULT_BIND_EVENT;	

	/**
	 * The unique id for the javascript element. This can be null, however in that case the ajax
	 * header contribution can't detect duplicate script fragments.
	 */
	private final String id;

	/** The javascript code to be rendered. */
	private final CharSequence javascript;

	/**
	 * Instantiates a new {@link JavascriptAppenderBehavior}. The id will be generated.
	 *
	 * @param javascript
	 *            javascript content to be add.
	 */
	public JavascriptAppenderBehavior(final CharSequence javascript)
	{
		this(String.valueOf(UUID.randomUUID()), javascript);
	}

	/**
	 * Instantiates a new {@link JavascriptAppenderBehavior}.
	 *
	 * @param javascript
	 *            javascript content to be add.
	 * @param id
	 *            unique id for the javascript element.
	 */
	public JavascriptAppenderBehavior(final String id, final CharSequence javascript)
	{
		this(id, javascript, null);
	}

	/**
	 * Instantiates a new {@link JavascriptAppenderBehavior}.
	 *
	 * @param javascript
	 *            javascript content to be add.
	 * @param id
	 *            unique id for the javascript element.
	 * @param bindEvent 
	 *            the bind event
	 */
	public JavascriptAppenderBehavior(final String id, final CharSequence javascript, JavascriptBindEvent bindEvent)
	{
		this.javascript = Args.notNull(javascript, "javascript");
		if(bindEvent != null) {
			this.bindEvent = bindEvent;
		}
		if (id == null)
		{
			this.id = String.valueOf(UUID.randomUUID());
		}
		else
		{
			this.id = id;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final Component component, final IHeaderResponse response)
	{
		super.renderHead(component, response);
		switch (bindEvent) {
		case ONDOMREADY:
			response.render(OnDomReadyHeaderItem.forScript(this.javascript));			
			break;
		case ONEVENT:
			response.render(OnEventHeaderItem.forScript(target, event, this.javascript));		
			break;
		case ONLOAD:
			response.render(OnLoadHeaderItem.forScript(this.javascript));	
			break;
		default:
			response.render(JavaScriptHeaderItem.forScript(this.javascript, this.id));
			break;
		}
	}

}
