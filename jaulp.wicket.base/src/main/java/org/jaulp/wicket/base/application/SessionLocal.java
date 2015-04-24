package org.jaulp.wicket.base.application;

import java.io.Serializable;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;
import org.apache.wicket.util.lang.Args;

/**
 * Stores an object with the given key into the wicket session. This class is like a ThreadLocal but
 * takes the wicket session instead of a local thread as context. When the wicket session is timed
 * out than the {@link org.jaulp.wicket.base.application.SessionLocal} will be destroyed.
 */
public class SessionLocal<T extends Serializable>
{

	/**
	 * The {@link org.apache.wicket.MetaDataKey} that is used to store an object.
	 */
	private final MetaDataKey<T> key;

	/**
	 * Constructor that takes a {@link org.apache.wicket.MetaDataKey} that is used to store an
	 * object.
	 * 
	 * @param key
	 *            The {@link org.apache.wicket.MetaDataKey} that is used to store an object.
	 */
	public SessionLocal(MetaDataKey<T> key)
	{
		this.key = Args.notNull(key, "key");
	}

	/**
	 * Gets the wicket session.
	 * 
	 * @return the wicket session.
	 */
	private Session getSession()
	{
		return Session.get();
	}

	/**
	 * Sets the given object in the MetaDataEntry.
	 * 
	 * @param value
	 *            The object to set.
	 */
	public void set(final T value)
	{
		getSession().setMetaData(key, value);
	}

	/**
	 * Gets the object from the MetaDataEntry.
	 * 
	 * @return
	 */
	public T get()
	{
		return getSession().getMetaData(key);
	}

	/**
	 * Sets the object to null in the MetaDataEntry.
	 */
	public void clear()
	{
		getSession().setMetaData(key, null);
	}
}
