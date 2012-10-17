package org.dropdownchoices.examples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Session;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.dropdownchoices.models.StringTwoDropDownChoicesModel;
import org.dropdownchoices.pages.TwoDropDownChoicesPage;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {

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

        final Link<String> link = new Link<String>("twoDropDownChoicesLink") {

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
		        ((WicketSession)Session.get()).setUserAttribute("stringTwoDropDownChoicesModel", stringTwoDropDownChoicesModel);
		        TwoDropDownChoicesPage twoDropDownChoicesPage = new TwoDropDownChoicesPage(pageParameters);
		        setResponsePage(twoDropDownChoicesPage);
			}
		};
        add( link );
        Label twoDropDownChoicesLbl = new Label("twoDropDownChoicesLbl", "Show two DropDownChoices page");
        link.add(twoDropDownChoicesLbl);

        final Link<String> moreExamplesLink = new Link<String>("moreExamplesLink") {

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
		        WicketWikiExample wicketWikiExample = new WicketWikiExample(pageParameters);
		        setResponsePage(wicketWikiExample);
			}
		};

		add( moreExamplesLink );
        Label moreExamplesLbl = new Label("moreExamplesLbl", "Show more examples with dropdown choices...");
        moreExamplesLink.add(moreExamplesLbl);

    }
}
