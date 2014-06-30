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
package org.jaulp.wicket.dropdownchoices.examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.dropdownchoices.pages.TwoDropDownChoicesPage;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {

        /** The models map. */
        Map< String, List< String >> modelsMap = new HashMap< String, List< String >>();

        modelsMap.put( "trademark.audi", Arrays.asList( new String [] {
                "audi.a4", "audi.a6", "audi.tt" } ) );
        modelsMap.put( "trademark.cadillac", Arrays.asList( new String [] {
                "cadillac.cts", "cadillac.dts", "cadillac.escalade",
                "cadillac.srx", "cadillac.deville" } ) );
        modelsMap.put(
                "trademark.ford",
                Arrays.asList( new String [] { "ford.crown", "ford.escape",
                        "ford.expedition", "ford.explorer", "ford.f_150" } ) );

        final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel = new StringTwoDropDownChoicesModel(
                "trademark.audi", modelsMap );

        final Link<String> link = new Link<String>("twoDropDownChoicesLink") {

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
		        ((WicketSession)Session.get()).setUserAttribute("stringTwoDropDownChoicesModel", stringTwoDropDownChoicesModel);
		        TwoDropDownChoicesPage twoDropDownChoicesPage = new TwoDropDownChoicesPage(pageParameters);
		        setResponsePage(twoDropDownChoicesPage);
			}
		};
        add( link );
        Label twoDropDownChoicesLbl = new Label("twoDropDownChoicesLbl", "Show two DropDownChoices page");
        link.add(twoDropDownChoicesLbl);

        final Link<String> moreExamplesLink = new Link<String>("moreExamplesLink") {

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
		        WicketWikiExample wicketWikiExample = new WicketWikiExample(pageParameters);
		        setResponsePage(wicketWikiExample);
			}
		};

		add( moreExamplesLink );
        Label moreExamplesLbl = new Label("moreExamplesLbl", "Show more examples with dropdown choices...");
        moreExamplesLink.add(moreExamplesLbl);

    }
}
