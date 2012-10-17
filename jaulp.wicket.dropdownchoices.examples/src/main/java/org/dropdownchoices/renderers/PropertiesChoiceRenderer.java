package org.dropdownchoices.renderers;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.dropdownchoices.models.SilentStringResourceModel;

public class PropertiesChoiceRenderer implements IChoiceRenderer< String > {
    private static final long serialVersionUID = 1L;

    /** The relative component used for lookups. */
    private Component component;

    /** The component class. */
    private Class< ? > componentClass;

    public PropertiesChoiceRenderer( final Component component,
            final Class< ? > componentClass ) {
        this.component = component;
        this.componentClass = componentClass;
    }

    public Object getDisplayValue( final String object ) {
        SilentStringResourceModel resourceModel = new SilentStringResourceModel(
                object, component, componentClass, Session.get().getLocale() );
        String value = resourceModel.getObject();
        return value;

    }

    public String getIdValue( final String object, final int index ) {
        return object;
    }
}