package org.jaulp.wicket.base.application;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class OnlineSessionUsers holds the mapping between the users that are
 * online and can be applied to get the functionality how many users are online.
 * It saves also the session object.
 *
 * @param <USER>
 *            the generic type for the users object.
 * @param <ID>
 *            the generic type for the id the references to the user object.
 * @param <SESSION>
 *            the generic type for the session object.
 */
public class OnlineSessionUsers<USER, ID, SESSION> extends
		OnlineUsers<USER, ID> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** This map holds which session id references to which session. */
	private final Map<ID, SESSION> sessionIdToSession = new HashMap<ID, SESSION>();

	/**
	 * Adds the user online.
	 *
	 * @param user
	 *            the user
	 * @param sessionId
	 *            the session id
	 * @param session
	 *            the session object
	 * @return the session id
	 */
	public synchronized ID addOnline(USER user, ID sessionId, SESSION session) {
		sessionIdToSession.put(sessionId, session);
		return super.addOnline(user, sessionId);
	}

	/**
	 * Replace the given old session id with the new one.
	 *
	 * @param user
	 *            the user
	 * @param oldSessionId
	 *            the old session id
	 * @param newSessionId
	 *            the new session id
	 * @param newSession
	 *            the new session object
	 * @return the new session id that is associated with the given user.
	 */
	public synchronized ID replaceSessionId(USER user, ID oldSessionId,
			ID newSessionId, SESSION newSession) {
		remove(oldSessionId);
		return addOnline(user, newSessionId, newSession);
	}

	/**
	 * Removes the user from the map. This method shell be invoked when the
	 * session is unbounded from the Application. In wicket is the best way to
	 * do that in the {@code WebApplication#sessionUnbound(String)}.
	 * 
	 * @param user
	 *            the user
	 * @return the session id
	 */
	public synchronized ID removeOnline(USER user) {
		ID sessionId = super.removeOnline(user);
		if (sessionId != null) {
			sessionIdToSession.remove(sessionId);
		}
		return sessionId;
	}

	/**
	 * Removes the user from the map with the session id.
	 *
	 * @param sessionId
	 *            the session id
	 * @return the user
	 */
	public synchronized USER remove(ID sessionId) {
		sessionIdToSession.remove(sessionId);
		return super.remove(sessionId);
	}

	/**
	 * Gets the session from the given session id.
	 *
	 * @param sessionId
	 *            the session id
	 * @return the session
	 */
	public synchronized SESSION getSession(ID sessionId) {
		return sessionIdToSession.get(sessionId);
	}

	/**
	 * Gets the session from the given user.
	 *
	 * @param user
	 *            the user
	 * @return the session
	 */
	public synchronized SESSION get(USER user) {
		return sessionIdToSession.get(getSessionId(user));
	}

}