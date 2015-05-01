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
package de.alpharogroup.wicket.base.application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class OnlineUsers holds the mapping between the users that are online and can be applied to
 * get the functionality how many users are online.
 *
 * @param <USER>
 *            the generic type for the users object.
 * @param <ID>
 *            the generic type for the id the references to the user object.
 */
public class OnlineUsers<USER, ID> implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** This map holds the users objects that are online. */
	private final Map<USER, ID> usersOnline = new HashMap<>();

	/** This map holds which session id references to which user. */
	private final Map<ID, USER> sessionIdToUser = new HashMap<>();

	/**
	 * Adds the user online.
	 * 
	 * @param user
	 *            the user
	 * @param sessionId
	 *            the session id
	 * @return the string
	 */
	public synchronized ID addOnline(USER user, ID sessionId)
	{
		sessionIdToUser.put(sessionId, user);
		return usersOnline.put(user, sessionId);
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
	 * @return the new session id that is associated with the given user.
	 */
	public synchronized ID replaceSessionId(USER user, ID oldSessionId, ID newSessionId)
	{
		remove(oldSessionId);
		return addOnline(user, newSessionId);
	}

	/**
	 * Removes the user from the map. This method shell be invoked when the session is unbounded
	 * from the Application. In wicket is the best way to do that in the
	 * {@code WebApplication#sessionUnbound(String)}.
	 * 
	 * @param user
	 *            the user
	 * @return the session id
	 */
	public synchronized ID removeOnline(USER user)
	{
		ID sessionId = usersOnline.remove(user);
		if (sessionId != null)
		{
			sessionIdToUser.remove(sessionId);
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
	public synchronized USER remove(ID sessionId)
	{
		USER user = getUser(sessionId);
		if (user != null)
		{
			usersOnline.remove(user);
		}
		sessionIdToUser.remove(sessionId);
		return user;
	}

	/**
	 * Checks if the given user is online.
	 * 
	 * @param user
	 *            the user
	 * @return true, if the user is online
	 */
	public boolean isOnline(USER user)
	{
		return usersOnline.containsKey(user);
	}

	/**
	 * Gets the session id.
	 *
	 * @param user
	 *            the user
	 * @return the session id
	 */
	public ID getSessionId(USER user)
	{
		return usersOnline.get(user);
	}

	/**
	 * Gets the user over the sessionId.
	 * 
	 * @param sessionId
	 *            the session id
	 * @return the user
	 */
	public USER getUser(ID sessionId)
	{
		return sessionIdToUser.get(sessionId);
	}

	/**
	 * Gets the size of the online users.
	 * 
	 * @return how many users are at this moment online.
	 */
	public int getSize()
	{
		return usersOnline.size();
	}
}