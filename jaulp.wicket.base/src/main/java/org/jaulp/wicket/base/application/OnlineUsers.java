package org.jaulp.wicket.base.application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class OnlineUsers holds the mapping between the users that are online 
 * and can be applied to get the functionality how many users are online.
 *
 * @param <USERS> the generic type for the users object.
 * @param <ID> the generic type for the id the references to the user object.
 */
public class OnlineUsers<USERS, ID> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** This map holds the users objects that are online. */
	private final Map<USERS, ID> usersOnline = new HashMap<USERS, ID>();

	/** This map holds which session id references to which user. */
	private final Map<ID, USERS> sessionIdToUser = new HashMap<ID, USERS>();

	/**
	 * Adds the user online.
	 * 
	 * @param user
	 *            the user
	 * @param sessionId
	 *            the session id
	 * @return the string
	 */
	public synchronized ID addUserOnline(USERS user, ID sessionId) {
		sessionIdToUser.put(sessionId, user);
		return usersOnline.put(user, sessionId);
	}

	/**
	 * Removes the user from the map.
	 * 
	 * @param user
	 *            the user
	 * @return the string
	 */
	public synchronized ID removeUserOnline(USERS user) {
		ID sessionId = usersOnline.remove(user);
		if (sessionId != null) {
			sessionIdToUser.remove(sessionId);
		}
		return sessionId;
	}

	/**
	 * Checks if the given user is online.
	 * 
	 * @param user
	 *            the user
	 * @return true, if the user is online
	 */
	public boolean isOnline(USERS user) {
		return usersOnline.containsKey(user);
	}

	/**
	 * Gets the user over the sessionId.
	 * 
	 * @param sessionId
	 *            the session id
	 * @return the user
	 */
	public USERS getUser(ID sessionId) {
		return sessionIdToUser.get(sessionId);
	}

	/**
	 * Gets the size of the online users.
	 * 
	 * @return how many users are at this moment online.
	 */
	public int getSize() {
		return usersOnline.size();
	}
}