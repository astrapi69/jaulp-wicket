package de.alpharogroup.wicket.components.feedback;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class ToastrFeedbackPanel extends FeedbackPanel
{

	private static final long serialVersionUID = 1L;

	public ToastrFeedbackPanel(final String id)
	{
		super(id);
	}

	public ToastrFeedbackPanel(final String id, final IFeedbackMessageFilter filter)
	{
		super(id, filter);
	}

}
