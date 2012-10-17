package org.dropdownchoices.examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.dropdownchoices.models.StringTwoDropDownChoicesModel;
import org.dropdownchoices.panel.TrademarksModelsPanel;
import org.dropdownchoices.panel.WicketWikiExamplePanel;
import org.dropdownchoices.panel.base.BaseDropDownChoicePanel;
import org.dropdownchoices.panel.localised.LocalisedDropDownChoicePanel;
import org.dropdownchoices.panel.simple.SimpleDropDownChoicePanel;
import org.dropdownchoices.panels.TwoDropDownChoicesPanel;
import org.dropdownchoices.renderers.PropertiesChoiceRenderer;
import org.dropdownchoices.three.choices.ThreeDropDownChoicesPanel;

/**
 * @author Asterios Raptis
 */
public class WicketWikiExample extends WebPage {

	private static final long serialVersionUID = 1L;
	private final FeedbackPanel feedback;

    public WicketWikiExample( final PageParameters params ) {
        super( params );

        // Construct form and feedback panel and hook them up
        feedback = new FeedbackPanel( "feedback" );
        add( feedback );

        SimpleDropDownChoicePanel simpleDropDownChoicePanel = new SimpleDropDownChoicePanel(
                "simpleDropDownChoicePanel" );

        add( simpleDropDownChoicePanel );

        BaseDropDownChoicePanel baseDropDownChoicePanel = new BaseDropDownChoicePanel(
                "baseDropDownChoicePanel" );

        add( baseDropDownChoicePanel );

        WicketWikiExamplePanel wicketWikiExamplePanel = new WicketWikiExamplePanel(
                "wicketWikiExamplePanel" );

        add( wicketWikiExamplePanel );

        /** The models map. */
        Map< String, List< String >> modelsMap = new HashMap< String, List< String >>();

        modelsMap.put( "trademark.audi", Arrays.asList( new String [] {
                "audi.a4", "audi.a6", "audi.tt" } ) );
        modelsMap.put( "trademark.cadillac", Arrays.asList( new String [] {
                "cadillac.cts", "cadillac.dts", "cadillac.escalade",
                "cadillac.srx", "cadillac.deville" } ) );
        modelsMap.put(
                "trademark.ford",
                Arrays.asList( new String [] { "ford.crown", "ford.escape",
                        "ford.expedition", "ford.explorer", "ford.f_150" } ) );

        final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel = new StringTwoDropDownChoicesModel(
                "trademark.audi", modelsMap );

        TwoDropDownChoicesPanel twoDropDownChoicesPanel = new TrademarksModelsPanel(
                "twoDropDownChoicesPanel", stringTwoDropDownChoicesModel,
                new PropertiesChoiceRenderer(this, this.getClass()),
                new PropertiesChoiceRenderer(this, this.getClass()) );
        add(twoDropDownChoicesPanel);

        LocalisedDropDownChoicePanel localisedDropDownChoicePanel = new LocalisedDropDownChoicePanel(
                "localisedDropDownChoicePanel" );
        add( localisedDropDownChoicePanel );

        ThreeDropDownChoicesPanel threeDropDownChoicesPanel = new ThreeDropDownChoicesPanel(
                "threeDropDownChoicesPanel" );
        add( threeDropDownChoicesPanel );
    }

    public FeedbackPanel getFeedback() {
        return feedback;
    }

}
