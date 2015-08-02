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
package de.alpharogroup.wicket.components.examples.alerts;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;

import de.alpharogroup.wicket.base.BasePanel;

public class AlertsPanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;

	public AlertsPanel(final String id)
	{
		super(id);
		final MarkupContainer mc = new WebMarkupContainer("alert-area");
		add(mc);
		final Button button = new Button("button");
		button.add(new AttributeModifier("onclick", "newAlert('success', 'Oh yeah!', 2000);"));
		add(button);

	}
}
