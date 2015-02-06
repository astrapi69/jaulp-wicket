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
package org.jaulp.wicket.data.provider;

import java.util.ArrayList;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class SortCollectionUtilsTest
{

	protected void setUp() throws Exception
	{
	}

	protected void tearDown() throws Exception
	{
	}

	@Test
	public void testSortList()
	{
		List<Person> persons = new ArrayList<>();
		Person obelix = new Person();
		obelix.setGender("male");
		obelix.setName("obelix");

		Person asterix = new Person();
		asterix.setGender("male");
		asterix.setName("asterix");

		Person miraculix = new Person();
		miraculix.setGender("male");
		miraculix.setName("miraculix");

		persons.add(obelix);
		persons.add(asterix);
		persons.add(miraculix);

		// Unsorted Persons...
		AssertJUnit.assertTrue(
			"Index of person 'obelix' should be <0> but was <" + persons.indexOf(obelix) + ">.",
			persons.indexOf(obelix) == 0);
		AssertJUnit.assertTrue(
			"Index of person 'asterix' should be <1> but was <" + persons.indexOf(asterix) + ">.",
			persons.indexOf(asterix) == 1);
		AssertJUnit.assertTrue(
			"Index of person 'miraculix' should be <2> but was <" + persons.indexOf(miraculix)
				+ ">.", persons.indexOf(miraculix) == 2);

		SortCollectionUtils.sortList(persons, "name", true);
		// Sorted Persons by name in ascending order...
		AssertJUnit.assertTrue(
			"Index of person 'obelix' should be <0> but was <" + persons.indexOf(obelix) + ">.",
			persons.indexOf(obelix) == 0);
		AssertJUnit.assertTrue(
			"Index of person 'miraculix' should be <1> but was <" + persons.indexOf(miraculix)
				+ ">.", persons.indexOf(miraculix) == 1);
		AssertJUnit.assertTrue(
			"Index of person 'asterix' should be <2> but was <" + persons.indexOf(asterix) + ">.",
			persons.indexOf(asterix) == 2);

		SortCollectionUtils.sortList(persons, "name", false);
		// Sorted Persons by name in descending order...
		AssertJUnit.assertTrue(
			"Index of person 'asterix' should be <0> but was <" + persons.indexOf(asterix) + ">.",
			persons.indexOf(asterix) == 0);
		AssertJUnit.assertTrue(
			"Index of person 'miraculix' should be <1> but was <" + persons.indexOf(miraculix)
				+ ">.", persons.indexOf(miraculix) == 1);
		AssertJUnit.assertTrue(
			"Index of person 'obelix' should be <2> but was <" + persons.indexOf(obelix) + ">.",
			persons.indexOf(obelix) == 2);
		// set a null value...
		asterix.setName(null);

		SortCollectionUtils.sortList(persons, "name", true);
		// Sorted Persons by name in ascending order with a null value...
		AssertJUnit.assertTrue(
			"Index of person 'obelix' should be <0> but was <" + persons.indexOf(obelix) + ">.",
			persons.indexOf(obelix) == 0);
		AssertJUnit.assertTrue(
			"Index of person 'miraculix' should be <1> but was <" + persons.indexOf(miraculix)
				+ ">.", persons.indexOf(miraculix) == 1);
		AssertJUnit.assertTrue(
			"Index of person 'asterix' should be <2> but was <" + persons.indexOf(asterix) + ">.",
			persons.indexOf(asterix) == 2);

	}

}
