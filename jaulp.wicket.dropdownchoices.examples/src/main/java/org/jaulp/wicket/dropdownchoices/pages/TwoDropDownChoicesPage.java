/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jaulp.wicket.dropdownchoices.pages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.util.WicketComponentUtils;
import org.jaulp.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import org.jaulp.wicket.components.i18n.dropdownchoice.renderers.PropertiesChoiceRenderer;
import org.jaulp.wicket.dropdownchoices.panel.TrademarksModelsPanel;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The class TwoDropDownChoicesPage.
 *
 * @author Asterios Raptis
 */
@ImportResources( resources =
{@ImportResource( resourceName = "TwoDropDownChoicesPage.css", resourceType = "css" )})
public class TwoDropDownChoicesPage extends WebPage {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

	private StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel;

    public StringTwoDropDownChoicesModel getStringTwoDropDownChoicesModel() {
		return stringTwoDropDownChoicesModel;
	}


	public void setStringTwoDropDownChoicesModel(
			StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel) {
		this.stringTwoDropDownChoicesModel = stringTwoDropDownChoicesModel;
	}


	public TwoDropDownChoicesPage( final PageParameters pageParameters ) {
        super( pageParameters );


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

        final CompoundPropertyModel< StringTwoDropDownChoicesModel > boundOptionModel = new CompoundPropertyModel< StringTwoDropDownChoicesModel >(
                stringTwoDropDownChoicesModel );

        final Form< StringTwoDropDownChoicesModel > selectOptionForm = new Form< StringTwoDropDownChoicesModel >(
                "selectOptionForm", boundOptionModel );

        add( selectOptionForm );

        TwoDropDownChoicesPanel twoDropDownChoicesPanel = new TrademarksModelsPanel(
                "twoDropDownChoicesPanel", stringTwoDropDownChoicesModel,
                new PropertiesChoiceRenderer(this, this.getClass()),
                new PropertiesChoiceRenderer(this, this.getClass()) );
        AttributeModifier sam = new AttributeModifier("style", "width: 200px; margin-bottom: 20px;");
        AttributeModifier samClass = new AttributeModifier("class", "nowrap");

        AttributeModifier samSize = new AttributeModifier("size", "3");

        twoDropDownChoicesPanel.getRootChoice().add(sam);
        twoDropDownChoicesPanel.getRootChoice().add(samSize);
        twoDropDownChoicesPanel.getRootChoice().add(samClass);

        twoDropDownChoicesPanel.getChildChoice().add(sam);
        twoDropDownChoicesPanel.getChildChoice().add(new AttributeModifier("size", "4"));

        selectOptionForm.add( twoDropDownChoicesPanel );

     // Create submit button for the form
        final Button entryButton = new Button( "entryButton" ) {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {

                System.out
                        .println( "RootOption:"
                                + stringTwoDropDownChoicesModel
                                        .getSelectedRootOption() );
                System.out.println( "ChildOption:"
                        + stringTwoDropDownChoicesModel
                                .getSelectedChildOption() );

            }
        };

        selectOptionForm.add( entryButton );

    }
	
    /**
     * {@inheritDoc}
     */
    public void renderHead(IHeaderResponse response) {
    	WicketComponentUtils.renderHeaderResponse(response, this.getClass());
    }

}
