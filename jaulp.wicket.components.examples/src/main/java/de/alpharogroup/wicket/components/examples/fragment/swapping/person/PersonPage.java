package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


@MountPath("public/swap/person")
public class PersonPage extends PubliclyBasePage<PersonModel> {
	private static final long serialVersionUID = 1L;

	public PersonPage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	public Component getContainerPanel() {
		return new PersonPanel(CONTAINER_PANEL_ID, Model.of(new PersonModel()));
	}
}
