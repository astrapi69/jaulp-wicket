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
package de.alpharogroup.wicket.components.examples.resource.references;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

/**
 * A JavaScript reference that loads the JavaScript resources needed by alerts UI components.
 */
public class AlertJsReference extends JQueryPluginResourceReference
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final AlertJsReference INSTANCE = new AlertJsReference();

	private AlertJsReference()
	{
		super(AlertJsReference.class, "js/bootstrap_alert.js");
	}

	@Override
	public List<HeaderItem> getDependencies()
	{
		final List<HeaderItem> deps = new ArrayList<HeaderItem>();
		for (final HeaderItem dep : super.getDependencies())
		{
			deps.add(dep);
		}
		return deps;
	}
}