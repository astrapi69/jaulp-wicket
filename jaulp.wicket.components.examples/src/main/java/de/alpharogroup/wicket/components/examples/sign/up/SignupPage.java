package de.alpharogroup.wicket.components.examples.sign.up;

import net.sourceforge.jaulp.auth.models.BaseUsernameSignUpModel;

import org.apache.wicket.Component;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.sign.up.SignupFormPanel;

@MountPath("public/signup")
public class SignupPage extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		return new SignupFormPanel(CONTAINER_PANEL_ID){
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSignup() {
				BaseUsernameSignUpModel model = (BaseUsernameSignUpModel) getDefaultModelObject();
				System.out.println(model);
			}			
		};
	}
}
