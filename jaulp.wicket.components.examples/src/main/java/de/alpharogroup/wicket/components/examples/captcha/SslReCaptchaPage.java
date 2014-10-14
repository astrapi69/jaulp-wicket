package de.alpharogroup.wicket.components.examples.captcha;

import org.apache.wicket.Component;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/sslrecaptcha")
@RequireHttps
public class SslReCaptchaPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new ReCaptchaFormPanel(CONTAINER_PANEL_ID);
	}

	public SslReCaptchaPage(final PageParameters parameters) {
 		super(parameters);		
	}
 
}