package de.alpharogroup.wicket.components.examples.fragment.replacewith;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonModel;

@MountPath("public/replace/with/panels")
public class ReplaceWithPage extends PubliclyBasePage<PersonModel> {

    /**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public ReplaceWithPage(final PageParameters parameters) {      
    }
	
	@Override
	public Component getContainerPanel() {
		return new ReplaceWithPanel(CONTAINER_PANEL_ID, Model.of(new PersonModel()));
	}
}