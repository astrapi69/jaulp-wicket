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
package de.alpharogroup.wicket.behaviors;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;

public class JQueryJsAppenderBehavior extends Behavior
{
	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(JQueryJsAppenderBehavior.class
		.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	CharSequence statementLabel;
	CharSequence statementArgs;

	public JQueryJsAppenderBehavior(final CharSequence statementLabel,
		final CharSequence statementArgs)
	{
		Args.notNull(statementLabel, "statementLabel");
		Args.notNull(statementArgs, "statementArgs");
		this.statementLabel = statementLabel;
		this.statementArgs = statementArgs;
	}

	public CharSequence createRenderedStatement(final Component component)
	{
		final JsStatement statement = new JsQuery(component).$().chain(statementLabel,
			JsUtils.quotes(statementArgs));
		// $('#component').statementLabel('statementArgs');
		return statement.render();
	}

	@Override
	public void renderHead(final Component component, final IHeaderResponse response)
	{
		super.renderHead(component, response);
		final CharSequence renderedStatement = createRenderedStatement(component);
		response.render(OnDomReadyHeaderItem.forScript(renderedStatement));
	}
}
