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
package de.alpharogroup.wicket.components.examples.home;

import org.apache.wicket.model.Model;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.behaviors.JQueryJsAppenderBehavior;
import de.alpharogroup.wicket.behaviors.datetime.CurrentDatetimeBehavior;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.i18n.content.ContentModelBean;
import de.alpharogroup.wicket.components.i18n.content.ContentPanel;

public class HomePanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;

	public HomePanel(final String id)
	{
		super(id);
		final ContentPanel contentPanel = new ContentPanel("contentPanel", Model.of(ContentModelBean
			.builder()
			.headerResourceKey(ResourceBundleKey.builder().key("home.header.label").build())
			.contentResourceKey(ResourceBundleKey.builder().key("home.content.label").build())
			.build()));
		contentPanel.getHeader().add(new JQueryJsAppenderBehavior("wrap", "<h1></h1>"));
		contentPanel.getContent().add(
			new JQueryJsAppenderBehavior("wrap", "<p class=\"lead\"></p>"));
		add(contentPanel);
		add(ComponentFactory.newLabel("currentTimeLabel", Model.of("")).add(
			new CurrentDatetimeBehavior()));

	}

}
