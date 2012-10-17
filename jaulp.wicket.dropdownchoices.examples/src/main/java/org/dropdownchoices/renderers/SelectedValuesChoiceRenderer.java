package org.dropdownchoices.renderers;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.dropdownchoices.models.SilentStringResourceModel;

public class SelectedValuesChoiceRenderer implements IChoiceRenderer< String > {

    private static final long serialVersionUID = 1L;

    /** The relative component used for lookups. */
    private Component component;

    /** The component class. */
    private Class< ? > componentClass;

    public SelectedValuesChoiceRenderer( final Component component,
            final Class< ? > componentClass ) {
        this.component = component;
        this.componentClass = componentClass;
    }

    public Object getDisplayValue( final String object ) {
        String splitString = "=>";
        String [] splittedValue = object.split( splitString );
        StringBuffer sb = new StringBuffer();
        SilentStringResourceModel resourceModel = new SilentStringResourceModel(
                splittedValue[ 0 ], component, componentClass, Session.get()
                        .getLocale() );
        sb.append( resourceModel.getObject() );
        sb.append( splitString );
        resourceModel = new SilentStringResourceModel( splittedValue[ 1 ],
                component, componentClass, Session.get().getLocale() );
        sb.append( resourceModel.getObject() );
        return sb.toString();
    }

    public String getIdValue( final String object, final int index ) {
        return object;
    }

}
