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

import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;

import de.alpharogroup.io.annotations.ImportResource;
import de.alpharogroup.io.annotations.ImportResources;
import de.alpharogroup.wicket.components.examples.basepage.ApplicationBasePanel;
import de.alpharogroup.wicket.components.examples.buttons.LocaleMenuPanel;
import de.alpharogroup.wicket.components.form.checkbox.image.ImageCheckboxModelBean;
import de.alpharogroup.wicket.components.form.checkbox.image.ImageChoicesModelBean;

/**
 * The Class CheckboxImageListViewPanel is an example with image with flags checkboxes.
 */
@ImportResources(resources = {
		@ImportResource(resourceName = "CheckboxImageListViewPanel.css", resourceType = "css", index = 1),
		@ImportResource(resourceName = "CheckboxImageListViewPanel.js", resourceType = "js", index = 2) })
public class CheckboxImageListViewPanel extends ApplicationBasePanel<List<ImageCheckboxModelBean>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new checkbox image list view panel.
	 *
	 * @param id
	 *            the id
	 */
	public CheckboxImageListViewPanel(final String id)
	{
		super(id, null);
		// add some dummy data
		final List<ImageCheckboxModelBean> choices = new ArrayList<>();
		choices.add(ImageCheckboxModelBean.builder()
			.imageResource(new PackageResourceReference(LocaleMenuPanel.class, "germany.gif"))
			.build());
		choices.add(ImageCheckboxModelBean.builder()
			.imageResource(new PackageResourceReference(LocaleMenuPanel.class, "britain.gif"))
			.build());
		choices.add(ImageCheckboxModelBean.builder()
			.imageResource(new PackageResourceReference(LocaleMenuPanel.class, "hellas.gif"))
			.build());
		final ImageChoicesModelBean imageChoicesModelBean = ImageChoicesModelBean.builder()
			.choices(choices).build();

		final ListView<ImageCheckboxModelBean> listView = new ListView<ImageCheckboxModelBean>(
			"list", imageChoicesModelBean.getChoices())
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<ImageCheckboxModelBean> item)
			{
				final ImageCheckboxModelBean wrapper = item.getModelObject();
				final CheckBox checkbox = new CheckBox("checkbox",
					model(from(wrapper).getChecked()));
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
