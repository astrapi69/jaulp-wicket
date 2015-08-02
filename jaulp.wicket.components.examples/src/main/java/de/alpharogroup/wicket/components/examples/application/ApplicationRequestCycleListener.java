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
package de.alpharogroup.wicket.components.examples.application;

import org.apache.wicket.request.component.IRequestablePage;

import de.alpharogroup.wicket.base.application.AbstractApplicationRequestCycleListener;
import de.alpharogroup.wicket.components.examples.exceptions.ExceptionPage;

public class ApplicationRequestCycleListener extends AbstractApplicationRequestCycleListener
{
	private static final long serialVersionUID = 1L;

	@Override
	public IRequestablePage getExceptionPage(final Exception e)
	{
		e.printStackTrace();
		return new ExceptionPage(e);
	}
}