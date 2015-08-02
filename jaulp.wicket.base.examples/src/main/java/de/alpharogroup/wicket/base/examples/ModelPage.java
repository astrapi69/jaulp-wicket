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
package de.alpharogroup.wicket.base.examples;


import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.test.objects.Person;
import de.alpharogroup.wicket.base.GenericBasePage;

@MountPath("/models")
public class ModelPage extends GenericBasePage<Person>
{
	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	IModel<String> nicknameModel;

	public ModelPage(final PageParameters parameters)
	{
		super(parameters);
		setModel(Model.of(new Person()));
		// set nickname null...
		getModelObject().setNickname(null);
		// Set PropertyModel to an Object that is null...
		nicknameModel = model(from(getModel()).getNickname());
		add(new MenubarPanel("menubarPanel"));
		final String nickname = nicknameModel.getObject();
		System.out.println("nickname is:" + nickname);
		add(new Label("text", nicknameModel).setOutputMarkupId(true));
		// TODO add an AjaxLink to change the nickname...

		final AjaxLink<Void> updateLabelLink = new AjaxLink<Void>("updateLabelLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				// set nickname to foo...
				ModelPage.this.getModelObject().setNickname("foo");
				System.out.println("nickname is:" + ModelPage.this.getModelObject().getNickname());
				target.add(ModelPage.this.get("text"));
			}
		};
		add(updateLabelLink);

	}

}
