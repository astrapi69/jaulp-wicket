package de.alpharogroup.wicket.components.examples.captcha;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.captcha.recaptcha.ReCaptchaPanel;

public class ReCaptchaFormPanel extends BasePanel<Object> {
	private static final long serialVersionUID = 1L;

	public ReCaptchaFormPanel(String id) {
		super(id);
		Form<?> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				info("Image words are valid!");
			};
		};

		form.add(new ReCaptchaPanel("reCaptchaPanel") {
			private static final long serialVersionUID = 1L;
			private final static String PUBLIC_KEY = "6LehBPwSAAAAAFtG28FJgzpkGhONCYDOmAzzopxz";
			private final static String PRIVATE_KEY = "6LehBPwSAAAAAN78oe6tLj2NqKhupf6vxhy8kkFJ";

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
