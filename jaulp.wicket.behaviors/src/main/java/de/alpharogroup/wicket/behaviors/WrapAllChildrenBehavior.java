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

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * The Class {@link WrapAllChildrenBehavior}. For instance:
 *
 * <pre>
 * $('#component').children().wrapAll('&lt;fieldset&gt;&lt;/fieldset&gt');
 * where statementLabel is 'wrapAll'
 * and statementArgs is '&lt;fieldset&gt;&lt;/fieldset&gt'
 * </pre>
 */
public class WrapAllChildrenBehavior extends Behavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The statement label. */
	CharSequence statementLabel;

	/** The statement args. */
	CharSequence statementArgs;

	/**
	 * Instantiates a new {@link WrapAllChildrenBehavior}.
	 *
	 * @param statementLabel
	 *            the statement label
	 * @param statementArgs
	 *            the statement args
	 */
	public WrapAllChildrenBehavior(final CharSequence statementLabel,
		final CharSequence statementArgs)
	{
		super();
		Args.notNull(statementLabel, "statementLabel");
		Args.notNull(statementArgs, "statementArgs");
		this.statementLabel = statementLabel;
		this.statementArgs = statementArgs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(final Component component, final IHeaderResponse response)
	{
		super.renderHead(component, response);
		final JsStatement statement = new JsQuery(component).$().chain("children")
			.chain(statementLabel, JsUtils.quotes(statementArgs));
		response.render(OnDomReadyHeaderItem.forScript(statement.render()));
	}
}
