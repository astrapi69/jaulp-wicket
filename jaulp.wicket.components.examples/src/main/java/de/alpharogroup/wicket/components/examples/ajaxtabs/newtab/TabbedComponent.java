package de.alpharogroup.wicket.components.examples.ajaxtabs.newtab;

import java.util.List;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;

public class TabbedComponent extends AjaxTabbedPanel implements IAjaxIndicatorAware{
	
         public TabbedComponent(String id, List tabs) {
		super(id, tabs);
		// TODO Auto-generated constructor stub
	}

		//removed some lines
        @Override
    public String getAjaxIndicatorMarkupId() {
        //Our tab will take care of the loading layer, so return null.
        return null;
    }
}