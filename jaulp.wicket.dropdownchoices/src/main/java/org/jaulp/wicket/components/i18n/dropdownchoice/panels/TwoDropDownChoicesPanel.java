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
package org.jaulp.wicket.components.i18n.dropdownchoice.panels;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The Class TwoDropDownChoicesPanel contains two dropdowns with a root and a child dropdown.
 *
 * @author Asterios Raptis
 */
public abstract class TwoDropDownChoicesPanel extends GenericPanel<StringTwoDropDownChoicesModel> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The root choice. */
    private final LocalisedDropDownChoice< String > rootChoice;

    /** The child choice. */
    private final LocalisedDropDownChoice< String > childChoice;

	/**
	 * Instantiates a new two drop down choices panel.
	 *
	 * @param id the id
	 * @param stringTwoDropDownChoicesModel the string two drop down choices model
	 * @param rootRenderer the root renderer
	 * @param childRenderer the child renderer
	 */
    public TwoDropDownChoicesPanel( final String id,
            final StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel,
            IChoiceRenderer< String > rootRenderer,
            IChoiceRenderer< String > childRenderer  ) {
        super( id );
        
        setModel(Model.of(stringTwoDropDownChoicesModel));
        
        rootChoice = newRootChoice(
                "rootChoice", new PropertyModel< String >(
                        stringTwoDropDownChoicesModel, "selectedRootOption" ),
                stringTwoDropDownChoicesModel.getRootChoices(),
                rootRenderer );

        childChoice = newChildChoice(
                "childChoice", new PropertyModel< String >(
                        stringTwoDropDownChoicesModel, "selectedChildOption" ),
                stringTwoDropDownChoicesModel.getChildChoices(),
                childRenderer);

        add( rootChoice );
        add( childChoice );
    }
    
	/**
	 * Factory method for the root choice.
	 *
	 * @param id the id
	 * @param model the model
	 * @param choices the choices
	 * @param renderer the renderer
	 * @return the localised drop down choice< string>
	 */
	protected LocalisedDropDownChoice<String> newRootChoice(
			final String id,
			final IModel<String> model,
			final IModel<? extends List<? extends String>> choices,
			final IChoiceRenderer<? super String> renderer) {
		LocalisedDropDownChoice<String> rc = new LocalisedDropDownChoice<String>(id, model, choices, renderer);
		rc.add(new AjaxFormComponentUpdatingBehavior( "onchange" ) {
            /** The Constant serialVersionUID. */
            private static final long serialVersionUID = 1L;
            @Override
            protected void onUpdate( final AjaxRequestTarget target ) {
                target.add( childChoice );
            }
        });
		return rc;
	}
    
	/**
	 * Factory method for the  child choice.
	 *
	 * @param id the id
	 * @param model the model
	 * @param choices the choices
	 * @param renderer the renderer
	 * @return the localised drop down choice< string>
	 */
	protected LocalisedDropDownChoice<String> newChildChoice(
			final String id,
			final IModel<String> model,
			final IModel<? extends List<? extends String>> choices,
			final IChoiceRenderer<? super String> renderer) {
		LocalisedDropDownChoice<String> cc = new LocalisedDropDownChoice<String>(id, model, choices, renderer);
		cc.setOutputMarkupId(true);
		return cc;
	}

	/**
	 * Gets the child choice.
	 *
	 * @return the child choice
	 */
	public LocalisedDropDownChoice<String> getChildChoice() {
		return childChoice;
	}

    /**
     * Gets the root choice.
     *
     * @return the root choice
     */
    public LocalisedDropDownChoice<String> getRootChoice() {
		return rootChoice;
	}
}
