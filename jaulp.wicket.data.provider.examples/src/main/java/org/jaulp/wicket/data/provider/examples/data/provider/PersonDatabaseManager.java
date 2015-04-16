package org.jaulp.wicket.data.provider.examples.data.provider;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jaulp.date.DatePatterns;
import net.sourceforge.jaulp.date.ParseDateUtils;

public final class PersonDatabaseManager
{


	public static final PersonDatabaseManager instance = new PersonDatabaseManager();
	private List<Person> persons;

	private PersonDatabaseManager()
	{
	}

	public static PersonDatabaseManager getInstance()
	{
		return instance;
	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	public List<Person> getPersons()
	{
		if (persons == null)
		{
			persons = new ArrayList<Person>();
			try
			{
				persons.add(new Person("Jamie", "Curtis", ParseDateUtils.parseToDate("12.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Toni", "Montana", ParseDateUtils.parseToDate("02.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Anton", "Pitt", ParseDateUtils.parseToDate("13.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Willy", "Lee", ParseDateUtils.parseToDate("03.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Bruce", "Willis", ParseDateUtils.parseToDate("14.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Henning", "Presley", ParseDateUtils.parseToDate(
					"04.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Michael", "Jackson", ParseDateUtils.parseToDate(
					"15.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Marco", "William", ParseDateUtils.parseToDate("05.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Gabriel", "Spears", ParseDateUtils.parseToDate(
					"16.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Kurt", "Russell", ParseDateUtils.parseToDate("06.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Ralph", "Crow", ParseDateUtils.parseToDate("17.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Peter", "Reilly", ParseDateUtils.parseToDate("07.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Asterix", "Nulty", ParseDateUtils.parseToDate("08.12.1950",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Obelix", "Bond", ParseDateUtils.parseToDate("18.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Miraculix", "James", ParseDateUtils.parseToDate(
					"09.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Darth", "Schnyder", ParseDateUtils.parseToDate(
					"19.12.1960", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Angela", "De Niro", ParseDateUtils.parseToDate(
					"10.12.1950", DatePatterns.DOT_DD_MM_YYYY)));
				persons.add(new Person("Brad", "Pacino", ParseDateUtils.parseToDate("21.12.1960",
					DatePatterns.DOT_DD_MM_YYYY)));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		return persons;
	}
}
