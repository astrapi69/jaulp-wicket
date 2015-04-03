package org.jaulp.wicket.behaviors;

import org.junit.Test;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.Options;

public class JqueryStatementsBehaviorTest
{

	@Test
	public void testCreateRenderedStatement()
	{
		CharSequence charSequence = new JsStatement().$().chain("trim", JsUtils.quotes("  abc "))
			.render();
		// $.trim(' abc ');
		System.out.println(charSequence);

		charSequence = new JsStatement().document().chain("ready", "function(){ alert('a'); }")
			.render();
		// $(document).ready(function(){
		// alert('a');
		// });
		System.out.println(charSequence);
		Options options = new Options().put("height", 300);
		charSequence = new JsStatement().document().chain("ready").css(options).render();
		// $(document).ready().css({height: 300});
		System.out.println(charSequence);
	}

	@Test
	public void testJsScope()
	{
		JsScope scope = new JsScope("event")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext)
			{
				scopeContext.append("alert('b with event:' + event);");
			}
		};
		CharSequence charSequence = scope.render();
		// @formatter:off
		// function(event){
		//      alert('b with event:' + event);
		// }
		// @formatter:on
		System.out.println(charSequence);
		charSequence = new JsScope()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void execute(JsScopeContext scopeContext)
			{
				scopeContext.self().chain("find", JsUtils.quotes("ul"));
			}
		}.render();
		// function() {
		// $(this).find('ul');
		// }
		System.out.println(charSequence);
	}

	@Test
	public void testJsQuery()
	{
		JsStatement jsQuery = new JsQuery().$(".foo").each(new JsScope()
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			public void execute(JsScopeContext scopeContext)
			{
				scopeContext.self().chain("css", "border", "1px solid red");
			}

		});
		CharSequence charSequence = jsQuery.render();
		// @formatter:off
		//		$('.foo').each(function() {
		//			$(this).css(border, 1px solid red);
		//		});
		// @formatter:on
		System.out.println(charSequence);

	}

	@Test
	public void testBehavior()
	{
		final JqueryStatementsBehavior wiQueryStatementBehavior = new JqueryStatementsBehavior()
			.add(
				new BuildableChainableStatement.Builder().label("find")
					.args(JsUtils.quotes("table:first-child")).build()).add(
				new BuildableChainableStatement.Builder().label("addClass")
					.args(JsUtils.quotes("tablefix")).build());
		// $('').find('table:first-child').addClass('tablefix');

		CharSequence renderedStatement = wiQueryStatementBehavior.createRenderedStatement(null);
		System.out.println(renderedStatement);
	}

}
