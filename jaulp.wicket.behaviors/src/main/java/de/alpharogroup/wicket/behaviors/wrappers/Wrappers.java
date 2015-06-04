package de.alpharogroup.wicket.behaviors.wrappers;

import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;


/**
 * The Class Wrappers.
 */
public final class Wrappers
{

	/**
	 * Private constructor.
	 */
	private Wrappers()
	{
	}

	/** The Constant PANEL_FOOTER_ELEMENT. */
	public static final JqueryStatementsBehavior PANEL_FOOTER_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"panel-footer\"></div>")).build());
	public static final JqueryStatementsBehavior FORM_GROUP_ELEMENT = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("wrap")
			.args(JsUtils.quotes("<div class=\"form-group\"></div>")).build());

	public static final JQueryJsAppenderBehavior CONTROL_GROUP_ELEMENT = new JQueryJsAppenderBehavior(
		"wrap", "<div class=\"control-group\"></div>");

	public static final JQueryJsAppenderBehavior ROW_ELEMENT = new JQueryJsAppenderBehavior("wrap",
		"<div class=\"row\"></div>");
}