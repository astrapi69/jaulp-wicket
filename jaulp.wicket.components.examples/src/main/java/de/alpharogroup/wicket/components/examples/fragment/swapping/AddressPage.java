package de.alpharogroup.wicket.components.examples.fragment.swapping;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


@MountPath("public/swap")
public class AddressPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	public AddressPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		return new AddressPanel(CONTAINER_PANEL_ID, Model.of(new HomeAddress()));
	}
}
