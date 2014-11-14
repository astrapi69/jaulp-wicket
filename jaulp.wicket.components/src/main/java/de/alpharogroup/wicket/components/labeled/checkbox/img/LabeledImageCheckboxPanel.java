package de.alpharogroup.wicket.components.labeled.checkbox.img;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.IResource;

public class LabeledImageCheckboxPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
		
	/** The CheckBox component. */
	private CheckBox checkBox;
	/** The image. */
	private Image image;

	public LabeledImageCheckboxPanel(String id, IModel<LabeledImageCheckboxModel> model) {
		super(id, model);
		PropertyModel<Boolean> propertyModel = new PropertyModel<Boolean>(model.getObject(), "checked");
        add(checkBox = newCheckBox("checkBox", propertyModel));
		add(image = newImage("image", model.getObject().getImageResource()));
	}
	
	/** 
	 * Factory method for creating the Image. This method is invoked in the
	 * constructor from this class and can be overridden so users can
	 * provide their own version of a Image.
	 *
	 * @param id the id
	 * @param imageResource the IResource object
	 * @return the created Image
	 */
	protected Image newImage(final String id, final IResource imageResource) {
		Image image = new Image("search_icon", imageResource);
		return image;
		
	}
	
	/** 
	 * Factory method for creating the CheckBox. This method is invoked in the
	 * constructor from this class and can be overridden so users can
	 * provide their own version of a CheckBox.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the created CheckBox
	 */
	protected CheckBox newCheckBox(String id, IModel<Boolean> model) {
		CheckBox checkBox = new CheckBox(id, model);
		checkBox.setOutputMarkupId(true);
		return checkBox;
	}

}
