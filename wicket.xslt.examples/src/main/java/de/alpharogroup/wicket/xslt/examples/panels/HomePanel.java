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
package de.alpharogroup.wicket.xslt.examples.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.transformer.XsltTransformerBehavior;
import org.apache.wicket.model.Model;


public class HomePanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HomePanel(final String id)
	{
		super(id);
		final Label report = new Label("message", new Model<>(

		"<message>Yep, it worked!</message>"));
		add(report);
		report.setEscapeModelStrings(false);
		final XsltTransformerBehavior trans = new XsltTransformerBehavior("def.xsl");
		trans.bind(report);
		report.add(trans);
	}
}