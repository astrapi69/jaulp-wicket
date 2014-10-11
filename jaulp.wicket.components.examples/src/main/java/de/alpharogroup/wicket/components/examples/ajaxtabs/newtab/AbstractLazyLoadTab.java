package de.alpharogroup.wicket.components.examples.ajaxtabs.newtab;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class AbstractLazyLoadTab extends AbstractTab {
         //removed some lines
 
    public AbstractLazyLoadTab(IModel<String> title) {
		super(title);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Panel getPanel(String panelId) {
        return new AjaxLazyLoadPanel(panelId){
 
            private static final long serialVersionUID = 1L;
 
            @Override
            public Component getLazyLoadComponent(String markupId) {
                return getLazyLoadPanel(markupId);
            }
        };
    }
 
        public abstract Panel getLazyLoadPanel(final String markupId);
}