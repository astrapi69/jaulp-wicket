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
package de.alpharogroup.wicket.components.examples.termofuse;

import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.termofuse.TermOfUseModelBean;
import de.alpharogroup.wicket.components.termofuse.TermOfUsePanel;


public class ApplicationTermOfUsePanel extends TermOfUsePanel
{

	private static final long serialVersionUID = 1L;

	public ApplicationTermOfUsePanel(final String id, final IModel<TermOfUseModelBean> model)
	{
		super(id, model);
	}
}