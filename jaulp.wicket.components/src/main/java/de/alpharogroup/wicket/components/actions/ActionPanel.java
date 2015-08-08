package de.alpharogroup.wicket.components.actions;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

/**
 * The Class {@link ActionPanel} for an specific action.
 *
 * @param <T>
 *            the generic type of the model
 */
public abstract class ActionPanel<T> extends AbstractActionPanel<T>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new {@link ActionPanel}.
	 *
	 * @param id
	 *            the component id
	 * @param model
	 *            the model
	 */
	public ActionPanel(final String id, final IModel<T> model)
	{
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractLink newActionLink(final String id)
	{
		final AjaxLink<String> link = new AjaxLink<String>(id)
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				ActionPanel.this.onAction(target);
			}
		};
		return link;
	}

	/**
	 * Abstract callback method that must be overwritten to provide specific action.
	 *
	 * @param target
	 *            the target
	 */
	protected abstract void onAction(final AjaxRequestTarget target);

}
