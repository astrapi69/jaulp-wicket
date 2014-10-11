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
        tester.assertComponent("panel", ReCaptchaPanel.class);
        tester.assertVisible("panel");
    }
}
