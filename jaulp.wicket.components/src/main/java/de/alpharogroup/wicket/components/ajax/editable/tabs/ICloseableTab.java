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
package de.alpharogroup.wicket.components.ajax.editable.tabs;

import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.model.IModel;

/**
 * The Interface {@link ICloseableTab} to provide closable ITab functionality.
 */
public interface ICloseableTab extends ITab
{

	/**
	 * Gets the close title.
	 *
	 * @return the close title
	 */
	IModel<String> getCloseTitle();
}