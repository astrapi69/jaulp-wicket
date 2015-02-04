package de.alpharogroup.wicket.components.termofuse.general;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;

import de.alpharogroup.wicket.components.i18n.list.HeaderContentListModel;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListPanel;

/**
 * The Class GeneralTermsAndConditionsPanel.
 */
public class GeneralTermsAndConditionsPanel extends HeaderContentListPanel
{


	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new general terms and conditions panel.
	 *
	 * @param id
	 *            the id
	 */
	public GeneralTermsAndConditionsPanel(String id)
	{
		this(id, null);
	}

	public GeneralTermsAndConditionsPanel(String id, IModel<HeaderContentListModel> model)
	{
		super(id, model);
	}

	@Override
	protected Component newListComponent(String id, ListItem<ResourceBundleKey> item)
	{
		return new Label(id, newContentResourceModel(item.getModel())).add(new AddJsQueryBehavior(
			"wrap", "<p></p>"));
	}

	@Override
	protected Component newHeaderLabel(String id, IModel<String> model)
	{
		return super.newHeaderLabel(id, model).add(new AddJsQueryBehavior("wrap", "<h1></h1>"));
	}

}
