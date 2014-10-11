package de.alpharogroup.wicket.components.examples.captcha;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.components.captcha.recaptcha.ReCaptchaPanel;

 
public class KaptchaPage extends WebPage {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KaptchaPage(final PageParameters parameters) {
 		 
		@SuppressWarnings("serial")
		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() { 
				info("Image words are valid!");
			};
		};		
 
//		form.add(new KaptchaPanel("kaptchaPanel", Model.of(new KaptchaModel())));

		form.add(
				new ReCaptchaPanel("reCaptchaPanel"){

			@Override
			public String getPublicKey() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getPrivateKey() {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		add(form);
		add(new FeedbackPanel("feedback"));
		
	}
 
}