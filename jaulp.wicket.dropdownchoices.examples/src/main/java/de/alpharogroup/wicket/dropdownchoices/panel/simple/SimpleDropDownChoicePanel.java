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
package de.alpharogroup.wicket.dropdownchoices.panel.simple;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Asterios Raptis
 */
public class SimpleDropDownChoicePanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleDropDownChoicePanel(final String id)
	{
		super(id);
		final IModel<List<String>> countries = new LoadableDetachableModel<List<String>>()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public List<String> load()
			{
				final List<String> l = new ArrayList<String>();
				l.add("Argentina");
				l.add("Brazil");
				l.add("Chile");
				return l;
			}
		};

		final DropDownChoice<String> country = new DropDownChoice<String>("country", countries);
		add(country);
	}

}
