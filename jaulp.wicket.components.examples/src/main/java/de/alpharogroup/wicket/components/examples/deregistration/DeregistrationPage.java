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
package de.alpharogroup.wicket.components.examples.deregistration;

import org.apache.wicket.Component;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.deregistration.DeregistrationModel;
import de.alpharogroup.wicket.components.deregistration.DeregistrationPanel;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/deregistration")
public class DeregistrationPage extends PubliclyBasePage<DeregistrationModel>
{
	private static final long serialVersionUID = 1L;

	public DeregistrationPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		final IModel<DeregistrationModel> model = new CompoundPropertyModel<DeregistrationModel>(
			new DeregistrationModel());
		return new DeregistrationPanel(CONTAINER_PANEL_ID, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onDeregistration()
			{
				// TODO action...
				IModel<DeregistrationModel> m = getModel();
				Object mo = getModelObject();
				System.out.println(mo + m.toString());
			}

			@Override
			public String getDomainName()
			{
				return "jaulp.wicket.components.org";
			}

		};
	}
}