package org.jaulp.wicket.data.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;

public final class SortCollectionUtils {

	private SortCollectionUtils(){

	}

	/**
	 * Sort.
	 *
	 * @param list the list
	 * @param property the property
	 * @param ascending the ascending
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static<T> void sortList(List<T> list, String property, boolean ascending) {
		Comparator comparator = new BeanComparator(property);
		if(ascending) {
			comparator = ComparatorUtils.reversedComparator(comparator);
		}
		Collections.sort(list, comparator);
	}

}
