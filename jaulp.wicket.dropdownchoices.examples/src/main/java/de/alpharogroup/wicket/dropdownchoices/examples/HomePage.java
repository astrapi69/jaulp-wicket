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
package de.alpharogroup.wicket.dropdownchoices.examples;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.dropdownchoices.pages.DatabaseManager;
import de.alpharogroup.wicket.dropdownchoices.pages.TwoDropDownChoicesPage;
import de.alpharogroup.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * Homepage
 */
public class HomePage extends WebPage
{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters)
	{
		final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel = new StringTwoDropDownChoicesModel(
			"trademark.audi", DatabaseManager.initializeModelMap());

		final Link<String> link = new Link<String>("twoDropDownChoicesLink")
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				final PageParameters pageParameters = new PageParameters();
				((WicketSession)Session.get()).setUserAttribute("stringTwoDropDownChoicesModel",
					stringTwoDropDownChoicesModel);
				final TwoDropDownChoicesPage twoDropDownChoicesPage = new TwoDropDownChoicesPage(
					pageParameters);
				setResponsePage(twoDropDownChoicesPage);
			}
		};
		add(link);
		final Label twoDropDownChoicesLbl = new Label("twoDropDownChoicesLbl",
			"Show two DropDownChoices page");
		link.add(twoDropDownChoicesLbl);

		final Link<String> moreExamplesLink = new Link<String>("moreExamplesLink")
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				final PageParameters pageParameters = new PageParameters();
				final WicketWikiExample wicketWikiExample = new WicketWikiExample(pageParameters);
				setResponsePage(wicketWikiExample);
			}
		};

		add(moreExamplesLink);
		final Label moreExamplesLbl = new Label("moreExamplesLbl",
			"Show more examples with dropdown choices...");
		moreExamplesLink.add(moreExamplesLbl);

	}
}
