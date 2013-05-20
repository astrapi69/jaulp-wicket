package org.dropdownchoices.panel.localised;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.dropdownchoices.models.OptionModel;
import org.dropdownchoices.renderers.LocalisedChoiceRenderer;
import org.jaulp.wicket.components.dropdownchoice.LocalisedDropDownChoice;

/**
 * The Class LocalisedDropDownChoicePanel.
 */
public class LocalisedDropDownChoicePanel extends Panel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private final Map< OptionModel, List< OptionModel >> modelsMap = new HashMap< OptionModel, List< OptionModel >>(); // map:company->model

    /**
     * Instantiates a new localised drop down choice panel.
     *
     * @param id the id
     */
    public LocalisedDropDownChoicePanel( final String id ) {
        super( id );

        OptionModel optionModel = new OptionModel();

        optionModel.setValue( "option.value.1" );

        final CompoundPropertyModel< OptionModel > boundOptionModel = new CompoundPropertyModel< OptionModel >(
                optionModel );

        final Form< OptionModel > selectOptionForm = new Form< OptionModel >(
                "selectOptionForm", boundOptionModel );

        add( selectOptionForm );
        // This should supplied from the database...
        List< String > values = Arrays.asList( "1", "2" );
        LocalisedDropDownChoice< String > ddc1 = new LocalisedDropDownChoice< String >(
                "options", new PropertyModel< String >( optionModel, "value" ),
                values, new LocalisedChoiceRenderer( "option.value", this, this
                        .getClass() ) );

        selectOptionForm.add( ddc1 );

        LocalisedDropDownChoice< String > ddc2 = new LocalisedDropDownChoice< String >(
                "options2",
                new PropertyModel< String >( optionModel, "value" ), values,
                new LocalisedChoiceRenderer( "option.value", this, this
                        .getClass() ) );

        // Create submit button for the form
        final Button entryButton = new Button( "entryButton" ) {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                boolean english = Session.get().getLocale().equals(
                        Locale.ENGLISH );
                if ( english ) {
                    Session.get().setLocale( Locale.GERMAN );
                } else {
                    Session.get().setLocale( Locale.ENGLISH );
                }
            }
        };
        selectOptionForm.add( entryButton );

    }

}
