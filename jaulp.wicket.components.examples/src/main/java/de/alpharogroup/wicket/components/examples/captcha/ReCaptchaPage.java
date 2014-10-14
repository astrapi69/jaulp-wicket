package de.alpharogroup.wicket.components.examples.captcha;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/recaptcha")
public class ReCaptchaPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new ReCaptchaFormPanel(CONTAINER_PANEL_ID);
	}

	public ReCaptchaPage(final PageParameters parameters) {
 		super(parameters);		
	}
 
}