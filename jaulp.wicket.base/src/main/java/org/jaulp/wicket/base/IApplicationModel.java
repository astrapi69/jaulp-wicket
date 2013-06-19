package org.jaulp.wicket.base;

import org.apache.wicket.Application;
import org.apache.wicket.Session;

/**
 * The Interface IApplicationModel holds methods for getting the Application and Session objects.
 *
 * @param <A> the generic type for the Wicket Application.
 * @param <S> the generic type for the Wicket Session.
 */
public interface IApplicationModel<A extends Application, S extends Session> {

	/**
	 * Gets the wicket application.
	 * 
	 * @return the wicket application
	 */
	A getWicketApplication();

	/**
	 * Gets the wicket session.
	 * 
	 * @return the wicket session
	 */
	S getWicketSession();

}