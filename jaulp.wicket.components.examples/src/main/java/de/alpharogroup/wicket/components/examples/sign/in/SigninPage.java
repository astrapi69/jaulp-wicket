package de.alpharogroup.wicket.components.examples.sign.in;

import net.sourceforge.jaulp.auth.models.BaseSignInModel;
import net.sourceforge.jaulp.auth.models.SignInModel;

import org.apache.wicket.Component;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.sign.in.form.SigninFormPanel;

@MountPath("public/signin")
public class SigninPage  extends PubliclyBasePage<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel() {
		final SignInModel model = new BaseSignInModel();

		final IModel<SignInModel> cpm = new CompoundPropertyModel<SignInModel>(
				model);
		SigninFormPanel signFormPanel = new SigninFormPanel(CONTAINER_PANEL_ID, cpm) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSignin() {
				System.out.println(model.getEmail());
				System.out.println(model.getPassword());
				
			}
		};
		return signFormPanel;
	}
}
