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
package org.jaulp.wicket.annotated.header.contributors.examples;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.annotated.header.contributors.abase.BasePage;
import org.jaulp.wicket.annotated.header.contributors.examples.panels.footer.FooterPanel;
import org.jaulp.wicket.annotated.header.contributors.examples.panels.home.HomePanel;

/**
 * Homepage
 */
@ImportResources( resources = {@ImportResource( resourceName = "HomePage.css", resourceType = "css", index = 0 )})
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
    	super(parameters);

    	add(new HomePanel("homePanel"));

    	add(new FooterPanel("footerPanel"));

    }

}