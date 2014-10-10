package de.alpharogroup.wicket.components.captcha.draw;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * The class CaptchaPanel.
 * 
 * @author Asterios Raptis
 */
public class CaptchaPanel extends Panel {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CaptchaPanel(String id, CaptchaModel captchaModel) {
        super(id);
        // Create a random Image...
        Image captchaImage = new Image("captchaImage",
                captchaModel.getCaptchaImageResource());
        // Create an TextField for the input...
        RequiredTextField<String> captchaInput = 
        		new RequiredTextField<String>("captchaInput", 
        				new PropertyModel<String>(
                        captchaModel.getProperties(), "captchaInput")) {

            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected final void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                // clear the field after each render
                tag.put("value", "");
            }
        };
        captchaInput.setType(String.class);
        // Add the image to the panel...
        add(captchaImage);
        // Add the TextField to the panel...
        add(captchaInput);
	}

}
