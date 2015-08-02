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
package de.alpharogroup.wicket.data.provider.examples.listview;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import de.alpharogroup.wicket.data.provider.examples.data.provider.Person;
import de.alpharogroup.wicket.data.provider.examples.data.provider.PersonDatabaseManager;

/**
 * @author admin
 */
public class ListViewPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public ListViewPanel(final String id)
	{
		super(id);
		final List<Person> persons = PersonDatabaseManager.getInstance().getPersons();
		final ListView<Person> listView = new ListView<Person>("listView", persons)
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Person> item)
			{
				item.setDefaultModel(new CompoundPropertyModel<Person>(item.getModel()));
				item.add(new Label("firstname"));
				item.add(new Label("lastname"));
				item.add(new Label("dateOfBirth"));
			}
		};
		add(listView);
	}

}
