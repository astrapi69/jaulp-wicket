package de.alpharogroup.wicket.components.examples.deregistration;
import org.apache.wicket.Component;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.deregistration.DeregistrationModel;
import de.alpharogroup.wicket.components.deregistration.DeregistrationPanel;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

@MountPath("public/deregistration")
public class DeregistrationPage extends PubliclyBasePage<DeregistrationModel> {
	private static final long serialVersionUID = 1L;

	public DeregistrationPage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	public Component getContainerPanel() {
		final IModel<DeregistrationModel> model = new CompoundPropertyModel<DeregistrationModel>(new DeregistrationModel());
		return new DeregistrationPanel(CONTAINER_PANEL_ID, model){
			private static final long serialVersionUID = 1L;

			@Override
			public void onDeregistration() {
				// TODO action...
				IModel m = getModel();
				Object mo = getModelObject();
				System.out.println(mo);
			}

			@Override
			public String getDomainName() {
				return "jaulp.wicket.components.org";
			}
			
		};
	}
}