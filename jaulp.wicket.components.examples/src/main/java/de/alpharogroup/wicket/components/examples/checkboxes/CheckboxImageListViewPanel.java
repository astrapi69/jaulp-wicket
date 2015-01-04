package de.alpharogroup.wicket.components.examples.checkboxes;

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
@ImportResources(resources = {
		@ImportResource(resourceName = "CheckboxImageListViewPanel.css", resourceType = "css", index = 1),
		@ImportResource(resourceName = "CheckboxImageListViewPanel.js", resourceType = "js", index = 2) })
public class CheckboxImageListViewPanel extends BasePanel {
	private static final long serialVersionUID = 1L;

	public CheckboxImageListViewPanel(String id) {
		super(id);

		// add some dummy data
		List<ImageCheckboxModel> data = new ArrayList<>();
		data.add(ImageCheckboxModel
				.builder()
				.imageResource(
						new PackageResourceReference(LocaleMenuPanel.class,
								"germany.gif")).build());
		data.add(ImageCheckboxModel
				.builder()
				.imageResource(
						new PackageResourceReference(LocaleMenuPanel.class,
								"britain.gif")).build());
		data.add(ImageCheckboxModel
				.builder()
				.imageResource(
						new PackageResourceReference(LocaleMenuPanel.class,
								"hellas.gif")).build());

		ListView<ImageCheckboxModel> listView = 
				new ListView<ImageCheckboxModel>("list", data) {
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			protected void populateItem(ListItem<ImageCheckboxModel> item) {
				ImageCheckboxModel wrapper = (ImageCheckboxModel) item
						.getModelObject();
				CheckBox checkbox = new CheckBox("checkbox", new PropertyModel<Boolean>(
						wrapper, "checked"));
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
