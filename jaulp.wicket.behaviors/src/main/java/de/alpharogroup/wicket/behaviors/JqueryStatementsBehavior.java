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

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.odlabs.wiquery.core.javascript.ChainableStatement;
import org.odlabs.wiquery.core.javascript.JsStatement;

public class JqueryStatementsBehavior extends Behavior
{
	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(JqueryStatementsBehavior.class
		.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private List<ChainableStatement> chainableStatement;
	@Getter
	private CharSequence renderedStatement;
	@Getter
	private boolean rendered;

	public JqueryStatementsBehavior()
	{
		this.chainableStatement = new ArrayList<>();
	}

	public JqueryStatementsBehavior(final List<ChainableStatement> chainableStatement)
	{
		Args.notNull(chainableStatement, "chainableStatement");
		this.chainableStatement = chainableStatement;
	}

	public JqueryStatementsBehavior add(final ChainableStatement defaultChainableStatement)
	{
		this.chainableStatement.add(defaultChainableStatement);
		return this;
	}

	public CharSequence createRenderedStatement(final Component component)
	{
		JsStatement statement;
		if (component != null)
		{
			component.setOutputMarkupId(true);
			statement = new JsStatement().$(component);
		}
		else
		{
			statement = new JsStatement();
		}

		for (final ChainableStatement defaultChainableStatement : chainableStatement)
		{
			statement.chain(defaultChainableStatement);
		}
		this.renderedStatement = statement.render();
		this.rendered = true;
		return this.renderedStatement;
	}

	@Override
	public void renderHead(final Component component, final IHeaderResponse response)
	{
		super.renderHead(component, response);
		final CharSequence renderedStatement = createRenderedStatement(component);
		response.render(OnDomReadyHeaderItem.forScript(renderedStatement));
	}
}
