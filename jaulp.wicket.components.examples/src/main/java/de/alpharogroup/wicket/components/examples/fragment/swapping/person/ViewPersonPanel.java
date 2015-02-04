package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ViewPersonPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ViewPersonPanel(String id, IModel<PersonModel> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		setDefaultModel(model);
		add(newEditLink("editLink"));
		add(new Label("firstName").setOutputMarkupId(true));
		add(new Label("lastName").setOutputMarkupId(true));
		add(new Label("gender").setOutputMarkupId(true));
		add(new Label("age").setOutputMarkupId(true));
	}

	protected AjaxFallbackLink<Object> newEditLink(String id)
	{
		return new AjaxFallbackLink<Object>(id)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				ViewPersonPanel.this.onSubmit(target);
			}
		};
	}

	protected void onSubmit(final AjaxRequestTarget target)
	{
	}

}
