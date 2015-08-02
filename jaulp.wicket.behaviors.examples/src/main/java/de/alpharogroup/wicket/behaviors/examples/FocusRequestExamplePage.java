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
package de.alpharogroup.wicket.behaviors.examples;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.behaviors.FocusRequestBehavior;

/**
 * FocusRequestExamplePage
 */
public class FocusRequestExamplePage extends WebPage
{

	private static final long serialVersionUID = 1L;

	// Add any page properties or variables here

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public FocusRequestExamplePage(final PageParameters parameters)
	{
		// message field
		final TextField<String> messageField = new TextField<String>("message");
		messageField.add(new FocusRequestBehavior());
		final Button button = new Button("button")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{

			}

		};
		add(messageField);
		add(button);

	}
}
