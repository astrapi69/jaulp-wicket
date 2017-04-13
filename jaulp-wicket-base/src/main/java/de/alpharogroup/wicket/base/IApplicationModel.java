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
package de.alpharogroup.wicket.base;

import org.apache.wicket.Application;
import org.apache.wicket.Session;

/**
 * The Interface IApplicationModel holds methods for getting the Application and Session objects.
 *
 * @param <A>
 *            the generic type for the Wicket Application.
 * @param <S>
 *            the generic type for the Wicket Session.
 */
public interface IApplicationModel<A extends Application, S extends Session>
{

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