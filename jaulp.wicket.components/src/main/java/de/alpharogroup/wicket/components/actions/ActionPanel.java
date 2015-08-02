package de.alpharogroup.wicket.components.actions;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * In use with DataTable for a column with a link that provides an action like select, delete etc.
 */
public abstract class ActionPanel<T> extends BasePanel<T>
{

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final String ACTION_LINK_ID = "actionLink";
	private static final String ACTION_LINK_LABEL_ID = "actionLinkLabel";

	/**
	 * @param id
	 *            component id
	 * @param model
	 *            model for contact
	 */
	public ActionPanel(final String id, final IModel<T> model)
	{
		super(id, model);
		add(newActionLink(ACTION_LINK_ID).add(
			newActionLinkLabel(ACTION_LINK_LABEL_ID, newActionLinkLabelModel())));
	}

	protected abstract AbstractLink newActionLink(final String id);

	protected Label newActionLinkLabel(final String id, final IModel<String> model)
	{
		final Label label = ComponentFactory.newLabel(id, model);
		return label;
	}

	protected abstract IModel<String> newActionLinkLabelModel();
}
