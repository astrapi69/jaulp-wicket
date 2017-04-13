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
package de.alpharogroup.wicket.components.actions;

import org.apache.wicket.util.io.IClusterable;

/**
 * The Interface Action implements the command Pattern.
 *
 * @author Asterios Raptis
 * @param <R>
 *            the generic type of the return type of the execute method.
 */
public interface Action<R> extends IClusterable
{

	/**
	 * Execute a command.
	 *
	 * @return the optional return type of the command.
	 */
	R execute();
}