package org.jaulp.wicket.data.provider;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class SortCollectionUtilsTest {

	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}
	@Test
	public void testSortList() {
		List<Person> persons = new ArrayList<Person>();
        Person obelix = new Person();
        obelix.setGender( "male" );
        obelix.setName( "obelix" );

        Person asterix = new Person();
        asterix.setGender( "male" );
        asterix.setName( "asterix" );

        Person miraculix = new Person();
        miraculix.setGender( "male" );
        miraculix.setName( "miraculix" );

        persons.add(obelix);
        persons.add(asterix);
        persons.add(miraculix);

        // Unsorted Persons...
        Assert.assertTrue("Index of person 'obelix' should be <0> but was <" +persons.indexOf(obelix) + ">.", persons.indexOf(obelix) == 0);
        Assert.assertTrue("Index of person 'asterix' should be <1> but was <" +persons.indexOf(asterix) + ">.", persons.indexOf(asterix) == 1);
        Assert.assertTrue("Index of person 'miraculix' should be <2> but was <" +persons.indexOf(miraculix) + ">.", persons.indexOf(miraculix) == 2);

        SortCollectionUtils.sortList(persons, "name", true);
        // Sorted Persons by name in ascending order...
        Assert.assertTrue("Index of person 'obelix' should be <0> but was <" +persons.indexOf(obelix) + ">.", persons.indexOf(obelix) == 0);
        Assert.assertTrue("Index of person 'miraculix' should be <1> but was <" +persons.indexOf(miraculix) + ">.", persons.indexOf(miraculix) == 1);
        Assert.assertTrue("Index of person 'asterix' should be <2> but was <" +persons.indexOf(asterix) + ">.", persons.indexOf(asterix) == 2);

        SortCollectionUtils.sortList(persons, "name", false);
        // Sorted Persons by name in descending order...
        Assert.assertTrue("Index of person 'asterix' should be <0> but was <" +persons.indexOf(asterix) + ">.", persons.indexOf(asterix) == 0);
        Assert.assertTrue("Index of person 'miraculix' should be <1> but was <" +persons.indexOf(miraculix) + ">.", persons.indexOf(miraculix) == 1);
        Assert.assertTrue("Index of person 'obelix' should be <2> but was <" +persons.indexOf(obelix) + ">.", persons.indexOf(obelix) == 2);


	}

}
