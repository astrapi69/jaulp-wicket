package org.jaulp.wicket.data.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;

public class AbstractSortFilterDataProvider<T extends Serializable, S extends Serializable, F extends Serializable>
	extends
		AbstractSortableDataProvider<T, S> implements IFilterStateLocator<F>
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The filter. */
	private F filterState;

	/**
	 * Default constructor.
	 */
	public AbstractSortFilterDataProvider()
	{
		super();
	}

	/**
	 * Instantiates a new AbstractSortFilterDataProvider.
	 *
	 * @param data
	 *            the data
	 */
	public AbstractSortFilterDataProvider(List<T> data)
	{
		super(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public F getFilterState()
	{
		return this.filterState;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFilterState(final F filterState)
	{
		this.filterState = filterState;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<? extends T> iterator(final long first, final long count)
	{
		List<T> ret = new ArrayList<>(filter(sort()));
		if (ret.size() > (first + count))
		{
			ret = ret.subList((int)first, (int)first + (int)count);
		}
		else
		{
			ret = ret.subList((int)first, ret.size());
		}
		return ret.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long size()
	{
		return filter(getData()).size();
	}

	/**
	 * Filter the given list. Override this method to implement a filter.
	 *
	 * @param found
	 *            the found
	 * @return the list
	 */
	protected List<T> filter(List<T> found)
	{
		return found;
	}

}
