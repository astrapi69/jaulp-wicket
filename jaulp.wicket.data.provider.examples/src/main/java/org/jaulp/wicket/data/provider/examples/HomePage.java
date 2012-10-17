package org.jaulp.wicket.data.provider.examples;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.jaulp.wicket.data.provider.examples.data.view.SortableDataViewPanel;
import org.jaulp.wicket.data.provider.examples.listview.ListViewPanel;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

	public HomePage(){
		super();

        initLayout();
	}

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
    	super(parameters);

        initLayout();


    }

	private void initLayout() {

        // Add sortable DataView...
        SortableDataViewPanel sortableDataViewPanel = new SortableDataViewPanel("sortableDataViewPanel");
        add(sortableDataViewPanel);

        // Add the ListView...
        add(new ListViewPanel("listViewPanel"));
	}
}
