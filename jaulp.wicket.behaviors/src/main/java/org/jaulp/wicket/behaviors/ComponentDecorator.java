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
package org.jaulp.wicket.behaviors;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.Component;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.behavior.Behavior;

/**
 * The Class ComponentDecorator decorates(wrappes) a component with the given
 * tag name and the given attributes that can be null if no attributes are
 * desired.
 */
public class ComponentDecorator extends Behavior {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tagname. */
	private String tagname;

	/** The attributes. */
	private Map<String, String> attributes;

	/**
	 * Instantiates a new component decorator.
	 * 
	 * @param tagname
	 *            the tagname
	 * @param attributes
	 *            the attributes
	 */
	public ComponentDecorator(String tagname, Map<String, String> attributes) {
		Args.notNull(tagname, "tagname");
		this.tagname = tagname;
		this.attributes = attributes;
	}

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
		Response r = component.getResponse();
		r.write("<" + tagname);
		if (attributes != null && !attributes.isEmpty()) {
			for (Entry<String, String> entry : attributes.entrySet()) {
				r.write(" " + entry.getKey() + "=" + "\"" + entry.getValue()
						+ "\"");
			}
		}
		r.write(">");
		super.beforeRender(component);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterRender(Component component) {
		super.afterRender(component);
		Response r = component.getResponse();
		r.write("</" + tagname + ">");
	}
}
