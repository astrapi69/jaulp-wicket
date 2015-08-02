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
package de.alpharogroup.wicket.components.examples.popupoverlay;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonBean;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonPanel;
import de.alpharogroup.wicket.js.addon.popupoverlay.PopupoverlayPanel;


@MountPath("public/popupoverlay")
public class PopupoverlayPage extends PubliclyBasePage<PersonBean>
{
	private static final long serialVersionUID = 1L;

	public PopupoverlayPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		final PopupoverlayPanel<PersonBean> popupoverlayPanel = new PopupoverlayPanel<PersonBean>(
			CONTAINER_PANEL_ID, Model.of(new PersonBean()))
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected Button newOpenButton(final String id)
			{
				final Button button = new Button(id);
				button.add(new AttributeAppender("class", " btn btn-primary"));
				return button;
			}

			@Override
			protected MarkupContainer newOverlayReference(final String id,
				final IModel<PersonBean> model)
			{
				final PersonPanel panel = new PersonPanel(id, model);
				panel.add(new AttributeAppender("class", " container well"));
				panel.setOutputMarkupId(true);
				return panel;
			}
		};

		return popupoverlayPanel;
	}
}
