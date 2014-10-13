package de.alpharogroup.wicket.components.captcha.recaptcha;

import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.wicket.components.examples.application.WicketApplication;

 
public class ReCaptchaPanelTest {
 
    private WicketTester tester;
 
    @BeforeMethod
    public void setUp() throws Exception {
        tester = new WicketTester(new WicketApplication());
    }
 
    @Test
    public void testRender() throws Exception {
        tester.startComponentInPage(new ReCaptchaPanel("panel"){
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
        tester.assertComponent("panel", ReCaptchaPanel.class);
        tester.assertVisible("panel");
    }
}
