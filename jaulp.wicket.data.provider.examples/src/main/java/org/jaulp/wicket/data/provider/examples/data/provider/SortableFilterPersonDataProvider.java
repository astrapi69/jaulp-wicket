package org.jaulp.wicket.data.provider.examples.data.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.jaulp.wicket.data.provider.AbstractSortFilterDataProvider;

public class SortableFilterPersonDataProvider extends
		AbstractSortFilterDataProvider<Person, String, PersonFilter> {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public SortableFilterPersonDataProvider() {
		this(new ArrayList<Person>());
	}

	public SortableFilterPersonDataProvider(List<Person> data) {
		super(data);
		setFilterState(new PersonFilter());
		setSort("firstname", SortOrder.ASCENDING);
	}

	@Override
	protected List<Person> filter(List<Person> personsFound) {
		return filterByFirstname(personsFound);
	}

	protected List<Person> filterByDateOfBirth(List<Person> personsFound) {
		ArrayList<Person> result = new ArrayList<>();
		Date dateFrom = getFilterState().getDateFrom();
		Date dateTo = getFilterState().getDateTo();
		for (Person person : personsFound) {
			Date bornDate = person.getDateOfBirth();
			if (dateFrom != null && bornDate.before(dateFrom)) {
				continue;
			}
			if (dateTo != null && bornDate.after(dateTo)) {
				continue;
			}
			result.add(person);
		}
		return result;
	}

	protected List<Person> filterByFirstname(List<Person> personsFound) {
		ArrayList<Person> result = new ArrayList<>();
		String filter = getFilterState().getFirstname();
		if(filter != null) {
			for (Person person : personsFound) {				
				if(person != null && 
						person.getFirstname()!=null && 
							person.getFirstname().matches(filter + "(.*)")) {
					result.add(person);
				}
			}
			return result;
		}
		return personsFound;
	}

}