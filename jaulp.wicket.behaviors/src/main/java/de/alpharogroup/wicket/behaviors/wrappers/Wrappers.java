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
package de.alpharogroup.wicket.behaviors.wrappers;

import org.odlabs.wiquery.core.javascript.JsUtils;

import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;


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