package org.jaulp.wicket.data.provider.examples.listview;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.jaulp.wicket.data.provider.examples.data.provider.Person;

/**
 * @author admin
 */
public class ListViewPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ListViewPanel(String id) {
		super(id);
		List<Person> persons =  new ArrayList<Person>();
		persons.add(new Person("Jamie", "Curtis", "11.12.1960"));
		persons.add(new Person("Toni", "Montana", "01.12.1950"));
		persons.add(new Person("Anton", "Pitt", "11.12.1960"));
		persons.add(new Person("Willy", "Lee", "01.12.1950"));
		persons.add(new Person("Bruce", "Willis", "11.12.1960"));
		persons.add(new Person("Henning", "Presley", "01.12.1950"));
		persons.add(new Person("Michael", "Jackson", "11.12.1960"));
		persons.add(new Person("Marco", "William", "01.12.1950"));
		persons.add(new Person("Gabriel", "Spears", "11.12.1960"));
		persons.add(new Person("Kurt", "Russell", "01.12.1950"));
		persons.add(new Person("Ralph", "Crow", "11.12.1960"));
		persons.add(new Person("Peter", "Reilly", "01.12.1950"));
		persons.add(new Person("Asterix", "Nulty", "11.12.1960"));
		persons.add(new Person("Obelix", "Bond", "01.12.1950"));
		persons.add(new Person("Miraculix", "James", "11.12.1960"));
		persons.add(new Person("Darth", "Schnyder", "01.12.1950"));
		persons.add(new Person("Angela", "De Niro", "11.12.1960"));
		persons.add(new Person("Brad", "Pacino", "01.12.1950"));
		ListView<Person> listView = new ListView<Person>("listView", persons){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Person> item) {
				item.setDefaultModel( new CompoundPropertyModel< Person >(
                        item.getModel() ) );
//				Person person = item.getModelObject();
                item.add( new Label( "firstname" ) );
                item.add( new Label( "lastname" ) );
                item.add( new Label( "dateOfBirth" ) );
			}
		};
		add(listView);
	}

}

