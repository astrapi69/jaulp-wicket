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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.jaulp.comparators.ComparableComparator;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;

public final class SortCollectionUtils
{

	private SortCollectionUtils()
	{

	}

	/**
	 * Sort.
	 *
	 * @param list
	 *            the list
	 * @param property
	 *            the property
	 * @param ascending
	 *            the ascending
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> void sortList(List<T> list, String property, boolean ascending)
	{
		Comparator comparator = new BeanComparator(property, new ComparableComparator());
		if (ascending)
		{
			comparator = ComparatorUtils.reversedComparator(comparator);
		}
		Collections.sort(list, comparator);
	}

}
