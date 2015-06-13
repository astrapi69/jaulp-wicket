package de.alpharogroup.wicket.components.examples.application;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import de.alpharogroup.wicket.base.util.SessionCountUtils;

public class WicketSession extends WebSession
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public WicketSession(Request request)
	{
		super(request);
	}	

	/**
	 * Gets the WicketSession for this application.
	 * 
	 * @return the current WicketSession object.
	 */
	public static WicketSession get() {
		return ((WicketSession) Session.get());
	}

	/**
	 * Gets the session timeout.
	 *
	 * @return the session timeout
	 */
	public int getSessionTimeout()
	{
		return SessionCountUtils.getSessionTimeout();
	}

}
