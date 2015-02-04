package de.alpharogroup.wicket.components.examples.popupoverlay;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.behaviors.popupoverlay.PopupoverlayPanel;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.PersonModel;
import de.alpharogroup.wicket.components.examples.fragment.swapping.person.ViewPersonPanel;


@MountPath("public/popupoverlay")
public class PopupoverlayPage extends PubliclyBasePage<PersonModel>
{
	private static final long serialVersionUID = 1L;

	public PopupoverlayPage(final PageParameters parameters)
	{
		super(parameters);
	}

	@Override
	public Component getContainerPanel()
	{
		return new PopupoverlayPanel<PersonModel>(CONTAINER_PANEL_ID, Model.of(new PersonModel()))
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			protected MarkupContainer newOverlayReference(String id, IModel<PersonModel> model)
			{
				ViewPersonPanel panel = new ViewPersonPanel(id, model);
				panel.add(new AttributeAppender("class", "overlay-panel"));
				panel.add(newPopupoverlayBehavior());
				panel.setOutputMarkupId(true);
				return panel;
			}


		};
	}
}
