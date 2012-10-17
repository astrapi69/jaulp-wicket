package org.jaulp.wicket.data.provider;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The Class AbstractDataProvider is an abstract generic implementation for the
 * ISortableDataProvider and the IFilterStateLocator interface. Note that the
 * given list of Models should not have fields that are 'null'. If one field is
 * 'null' the filter will be 'null' too and you will get a NPE.
 *
 * @param <F> the generic type for the Filter.
 * @param <M> the generic type for the Model.
 * @author Asterios Raptis
 */
public abstract class AbstractDataProvider< F, S, M extends Serializable >
        implements ISortableDataProvider< M, S >, IFilterStateLocator< F > {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /** The data. */
    private List< M > data;

    /** The filter. */
    private F filter;

    /** The list. */
    private List< M > list;

    /** The sort state. */
    private ISortState sortState = new SingleSortState();

    /**
     * Instantiates a new abstract data provider.
     *
     * @param data the data
     */
    public AbstractDataProvider( final List< M > data ) {
        super();
        this.data = data;
    }

    /**
     * {@inheritDoc}.
     *
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    @Override
    public void detach() {
        list = null;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    protected List< M > getData() {
        return data;
    }

    /**
     * {@inheritDoc}.
     *
     * @return the filter state
     * @see org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator#getFilterState()
     */
    @Override
    public F getFilterState() {
        return filter;
    }

    /**
     * Gets the list.
     *
     * @return the list
     */
    public List< M > getList() {
        return list;
    }

    /**
     * {@inheritDoc}.
     *
     * @return the sort state
     * @see org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator#getSortState()
     */
    @Override
    public ISortState getSortState() {
        return sortState;
    }

    /**
     * Inits the list.
     */
    protected abstract void initList();

    /**
     * {@inheritDoc}.
     *
     * @param first the first
     * @param count the count
     * @return the iterator
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#iterator(int,
     *      int)
     */
    public Iterator< ? extends M > iterator( final long first, final long count ) {
        initList();
        List< M > ret = list;
        if ( ret.size() > ( first + count ) ) {
            ret = ret.subList( (int)first, (int)first + (int)count );
        } else {
        	ret = ret.subList((int)first, ret.size());
        }
        return ret.iterator();
    }

    /**
     * {@inheritDoc}.
     *
     * @param object the object
     * @return the i model
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#model(java.lang.Object)
     */
    @Override
    public IModel< M > model( final M object ) {
        return Model.of( object );
    }

    /**
     * {@inheritDoc}.
     *
     * @param state the new filter state
     * @see org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator#setFilterState(java.lang.Object)
     */
    @Override
    public void setFilterState( final F state ) {
        filter = state;

    }

    /**
     * Sets the list.
     *
     * @param list the new list
     */
    protected void setList( final List< M > list ) {
        this.list = list;
    }

    /**
     * {@inheritDoc}.
     *
     * @param state the new sort state
     * @see org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator#setSortState(org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState)
     */
    public void setSortState( final ISortState state ) {
        sortState = state;
    }

    /**
     * {@inheritDoc}.
     *
     * @return the int
     * @see org.apache.wicket.markup.repeater.data.IDataProvider#size()
     */
    public long size() {
        initList();
        return list.size();
    }

}
