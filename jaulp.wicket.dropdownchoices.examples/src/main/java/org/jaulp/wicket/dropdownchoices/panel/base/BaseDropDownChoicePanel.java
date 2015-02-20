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
package org.jaulp.wicket.dropdownchoices.panel.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.jaulp.wicket.dropdownchoices.models.Country;


/**
 * @author Asterios Raptis
 */
public class BaseDropDownChoicePanel extends Panel
{
	private static final long serialVersionUID = 1L;

	public BaseDropDownChoicePanel(final String id)
	{
		super(id);
		IModel<List<Country>> countries = new LoadableDetachableModel<List<Country>>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public List<Country> load()
			{
				List<Country> l = new ArrayList<>();
				l.add(new Country("ar", "Argentina"));
				l.add(new Country("br", "Brazil"));
				l.add(new Country("cl", "Chile"));
				return l;
			}
		};
		IChoiceRenderer<Country> renderer = new IChoiceRenderer<Country>()
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Object getDisplayValue(Country object)
			{
				return object.getName();
			}

			@Override
			public String getIdValue(Country object, int index)
			{
				return object.getDigraph();
			}

			@Override
	        public Country getObject(String id, IModel<? extends List<? extends Country>> choices) {
	          List<? extends Country> c = choices.getObject();
	          for (Country country : c) {
	            if (country.equals(id)) {
	              return country;
	            }
	          }
	          return null;
	        }
		};

		DropDownChoice<Country> country = new DropDownChoice<Country>("country", countries);
		add(country);
		country.setChoiceRenderer(renderer);

	}

}
