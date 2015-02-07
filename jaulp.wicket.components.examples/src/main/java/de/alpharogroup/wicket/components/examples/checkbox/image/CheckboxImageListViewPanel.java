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
package de.alpharogroup.wicket.components.examples.checkbox.image;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.examples.buttons.LocaleMenuPanel;
import de.alpharogroup.wicket.components.form.checkbox.image.ImageCheckboxModel;
import de.alpharogroup.wicket.components.form.checkbox.image.ImageChoicesModel;

@ImportResources(resources = {
		@ImportResource(resourceName = "CheckboxImageListViewPanel.css", resourceType = "css", index = 1),
		@ImportResource(resourceName = "CheckboxImageListViewPanel.js", resourceType = "js", index = 2) })
public class CheckboxImageListViewPanel extends BasePanel<List<ImageCheckboxModel>>
{
	private static final long serialVersionUID = 1L;

	public CheckboxImageListViewPanel(String id)
	{
		super(id);
		// add some dummy data
		List<ImageCheckboxModel> choices = new ArrayList<>();
		choices.add(ImageCheckboxModel.builder()
			.imageResource(new PackageResourceReference(LocaleMenuPanel.class, "germany.gif"))
			.build());
		choices.add(ImageCheckboxModel.builder()
			.imageResource(new PackageResourceReference(LocaleMenuPanel.class, "britain.gif"))
			.build());
		choices.add(ImageCheckboxModel.builder()
			.imageResource(new PackageResourceReference(LocaleMenuPanel.class, "hellas.gif"))
			.build());
		ImageChoicesModel imageChoicesModel = ImageChoicesModel.builder().choices(choices).build();

		ListView<ImageCheckboxModel> listView = new ListView<ImageCheckboxModel>("list",
			imageChoicesModel.getChoices())
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<ImageCheckboxModel> item)
			{
				ImageCheckboxModel wrapper = item.getModelObject();
				CheckBox checkbox = new CheckBox("checkbox", new PropertyModel<Boolean>(wrapper,
					"checked"));
				checkbox.setOutputMarkupId(true);
				checkbox.add(new AttributeAppender("name", Model.of("cb")));
				item.add(checkbox);
				item.add(new Image("image", wrapper.getImageResource()));
			}
		};
		listView.setReuseItems(true);
		add(listView);
	}
}
