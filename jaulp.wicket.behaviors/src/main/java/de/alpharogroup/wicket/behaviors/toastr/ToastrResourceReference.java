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
package de.alpharogroup.wicket.behaviors.toastr;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * The Class ToastrResourceReference. There is a map file 'toastr.js.map' so add to the guard the
 * suffix pattern('+*.js.map') to accept the file.
 */
public class ToastrResourceReference extends JavaScriptResourceReference
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

  public static final ToastrResourceReference INSTANCE = new ToastrResourceReference();

  /**
   * Instantiates a new toastr resource reference.
   */
  private ToastrResourceReference()
	{
		super(ToastrResourceReference.class, "toastr.min.js");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<? extends HeaderItem> getDependencies()
	{
		List<HeaderItem> dependencies = new ArrayList<HeaderItem>();
		dependencies.add(CssHeaderItem.forReference(new CssResourceReference(
			ToastrResourceReference.class, "toastr.min.css")));
		return dependencies;
	}

}
