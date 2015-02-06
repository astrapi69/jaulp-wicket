package org.jaulp.wicket.behaviors;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;

public class AddJsQueryBehavior extends Behavior
{
	/** The Constant logger. */
	protected static final Logger LOGGER = Logger.getLogger(AddJsQueryBehavior.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	CharSequence statementLabel;
	CharSequence statementArgs;

	public AddJsQueryBehavior(CharSequence statementLabel, CharSequence statementArgs)
	{
		Args.notNull(statementLabel, "statementLabel");
		Args.notNull(statementArgs, "statementArgs");
		this.statementLabel = statementLabel;
		this.statementArgs = statementArgs;
	}

        @Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		CharSequence renderedStatement = createRenderedStatement(component);
		response.render(OnDomReadyHeaderItem.forScript(renderedStatement));
	}

	public CharSequence createRenderedStatement(Component component)
	{
		JsStatement statement = new JsQuery(component).$().chain(statementLabel,
			JsUtils.quotes(statementArgs));
		// $('#component').statementLabel('statementArgs');
		return statement.render();
	}
}