package org.jaulp.wicket.model.dropdownchoices;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Class StringThreeDropDownChoicesModel.
 * 
 * @author Asterios Raptis
 */
public class StringThreeDropDownChoicesModel extends
        ThreeDropDownChoicesModel< String > {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new string three drop down choices model.
     * 
     * @param selectedTrademark the selected trademark
     * @param modelsMap the models map
     */
    public StringThreeDropDownChoicesModel( final String selectedTrademark,
            final Map< String, List< String >> modelsMap ) {
        this( selectedTrademark, modelsMap, new ArrayList< String >() );
    }

    /**
     * Instantiates a new string three drop down choices model.
     * 
     * @param selectedTrademark the selected trademark
     * @param modelsMap the models map
     * @param selectedValuesChoices the selected values choices
     */
    public StringThreeDropDownChoicesModel( final String selectedTrademark,
            final Map< String, List< String >> modelsMap,
            final List< String > selectedValuesChoices ) {
        super( selectedTrademark, modelsMap, selectedValuesChoices );
    }

}