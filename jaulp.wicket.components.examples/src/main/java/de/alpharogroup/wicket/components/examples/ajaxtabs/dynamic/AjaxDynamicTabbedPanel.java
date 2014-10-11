package de.alpharogroup.wicket.components.examples.ajaxtabs.dynamic;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
 
/**
 * Ajax based extension of the dynamic tabbed panel
 *
 * @author Jonny Wray
 */
public class AjaxDynamicTabbedPanel extends DynamicTabbedPanel {

	private static final long serialVersionUID = 1L;

	public AjaxDynamicTabbedPanel(final String id, final IModel<List<ITab>> tabModel){
		super(id, tabModel);
		setOutputMarkupId(true);
		setVersioned(false);
	}
 
	@Override
	protected WebMarkupContainer newLink(final String linkId, final int index){
		return new AjaxFallbackLink<Void>(linkId)
		{
 
			private static final long serialVersionUID = 1L;
 
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				setSelectedTab(index);
				if (target != null){
					target.add(AjaxDynamicTabbedPanel.this);
				}
				onAjaxUpdate(target);
			}
 
		};
	}
 
	/**
	 * A template method that lets users add additional behavior when ajax update occurs. This
	 * method is called after the current tab has been set so access to it can be obtained via
	 * {@link #getSelectedTab()}.
	 * <p>
	 * <strong>Note</strong> Since an {@link AjaxFallbackLink} is used to back the ajax update the
	 * <code>target</code> argument can be null when the client browser does not support ajax and
	 * the fallback mode is used. See {@link AjaxFallbackLink} for details.
	 *
	 * @param target
	 *            ajax target used to update this component
	 */
	protected void onAjaxUpdate(final AjaxRequestTarget target)
	{
	}
}