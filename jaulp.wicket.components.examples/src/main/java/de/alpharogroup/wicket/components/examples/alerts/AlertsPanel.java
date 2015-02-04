package de.alpharogroup.wicket.components.examples.alerts;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.jaulp.wicket.base.BasePanel;

public class AlertsPanel extends BasePanel<Object>
{
	private static final long serialVersionUID = 1L;

	public AlertsPanel(String id)
	{
		super(id);
		MarkupContainer mc = new WebMarkupContainer("alert-area");
		add(mc);
		Button button = new Button("button");
		button.add(new AttributeModifier("onclick", "newAlert('success', 'Oh yeah!', 2000);"));
		add(button);

	}
}
