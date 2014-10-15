package org.jaulp.wicket.behaviors;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.util.lang.Args;
import org.odlabs.wiquery.core.javascript.DefaultChainableStatement;
import org.odlabs.wiquery.core.javascript.JsStatement;

public class AddJsQueryStatementsBehavior extends Behavior {
	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(AddJsQueryStatementsBehavior.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	List<DefaultChainableStatement> chainableStatement;
	
	public AddJsQueryStatementsBehavior() {
		this.chainableStatement = new ArrayList<DefaultChainableStatement>();
	}
	
	public AddJsQueryStatementsBehavior(List<DefaultChainableStatement> chainableStatement) {
		Args.notNull(chainableStatement, "chainableStatement");
		this.chainableStatement = chainableStatement;
	}

	public void renderHead(Component component, IHeaderResponse response) {
		CharSequence renderedStatement = createRenderedStatement(component);
		LOGGER.info(renderedStatement);
		response.render(OnDomReadyHeaderItem.forScript(renderedStatement));
	}
	
	public AddJsQueryStatementsBehavior add(DefaultChainableStatement defaultChainableStatement) {
		this.chainableStatement.add(defaultChainableStatement);
		return this;
	}
	
	public CharSequence createRenderedStatement(Component component) {
		component.setOutputMarkupId(true);
		JsStatement statement = new JsStatement().$(component);
		for (DefaultChainableStatement defaultChainableStatement : chainableStatement) {
			statement.chain(defaultChainableStatement);		
		}
		return statement.render();		
	}
}