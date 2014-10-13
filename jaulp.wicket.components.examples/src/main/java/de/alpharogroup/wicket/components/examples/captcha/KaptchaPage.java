package de.alpharogroup.wicket.components.examples.captcha;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.captcha.recaptcha.ReCaptchaPanel;

public class KaptchaPage extends WebPage {
	
	private static final long serialVersionUID = 1L;

	public KaptchaPage(final PageParameters parameters) {
 		 
		@SuppressWarnings("serial")
		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() { 
				info("Image words are valid!");
			};
		};		

		form.add(
				new ReCaptchaPanel("reCaptchaPanel") {
				private static final long serialVersionUID = 1L;
				private final static String PUBLIC_KEY = "6LcqB-4SABBBAB6MSipNmWQP6mCZ5HAEudHkF9Ra";
				private final static String PRIVATE_KEY = "6LcqB-4SABBBAFxigQz5wkWu1_lQnn8CtoCAmujA";
				@Override
				public String getPublicKey() {
					return PUBLIC_KEY;
				}
				@Override
				public String getPrivateKey() {
					return PRIVATE_KEY;
				}
			
		});
		add(form);
		add(new FeedbackPanel("feedback"));
		
	}
 
}