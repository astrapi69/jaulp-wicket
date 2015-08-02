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
package de.alpharogroup.wicket.data.provider.examples.pages.datatable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.data.provider.examples.datatable.DataTablePanel;

public class DataTablePage extends WebPage
{

	private static final long serialVersionUID = 1L;

	public DataTablePage()
	{
		super();

		initLayout();
	}

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public DataTablePage(final PageParameters parameters)
	{
		super(parameters);

		initLayout();


	}

	private void initLayout()
	{
		final DataTablePanel dataTablePanel = new DataTablePanel("dataTablePanel");
		add(dataTablePanel);


	}
}
