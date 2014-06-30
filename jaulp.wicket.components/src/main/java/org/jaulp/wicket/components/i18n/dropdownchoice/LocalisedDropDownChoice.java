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
package org.jaulp.wicket.components.i18n.dropdownchoice;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

/**
 * The Class LocalisedDropDownChoice extends the DropDownChoice. It overwrites the
 * method localizeDisplayValues to get the values from the appropriate
 * properties file. You can set the flag 'defaultChoice' to true to overwrite
 * the default behavior and showing 'choose'.
 *
 * @param <T> the generic type
 * @author Asterios Raptis
 */
public class LocalisedDropDownChoice< T > extends DropDownChoice< T > {

    /** The default choice. */
    public boolean defaultChoice;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     */
    public LocalisedDropDownChoice( final String id ) {
        super( id );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param choices the choices
     */
    public LocalisedDropDownChoice( final String id,
            final IModel< ? extends List< ? extends T >> choices ) {
        super( id, choices );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param choices the choices
     * @param renderer the renderer
     */
    public LocalisedDropDownChoice( final String id,
            final IModel< ? extends List< ? extends T >> choices,
            final IChoiceRenderer< ? super T > renderer ) {
        super( id, choices, renderer );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param model the model
     * @param choices the choices
     */
    public LocalisedDropDownChoice( final String id, final IModel< T > model,
            final IModel< ? extends List< ? extends T >> choices ) {
        super( id, model, choices );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param model the model
     * @param choices the choices
     * @param renderer the renderer
     */
    public LocalisedDropDownChoice( final String id, final IModel< T > model,
            final IModel< ? extends List< ? extends T >> choices,
            final IChoiceRenderer< ? super T > renderer ) {
        super( id, model, choices, renderer );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param model the model
     * @param choices the choices
     */
    public LocalisedDropDownChoice( final String id, final IModel< T > model,
            final List< ? extends T > choices ) {
        super( id, model, choices );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param model the model
     * @param data the data
     * @param renderer the renderer
     */
    public LocalisedDropDownChoice( final String id, final IModel< T > model,
            final List< ? extends T > data,
            final IChoiceRenderer< ? super T > renderer ) {
        super( id, model, data, renderer );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param choices the choices
     */
    public LocalisedDropDownChoice( final String id,
            final List< ? extends T > choices ) {
        super( id, choices );
    }

    /**
     * Instantiates a new localed drop down choice.
     *
     * @param id the id
     * @param data the data
     * @param renderer the renderer
     */
    public LocalisedDropDownChoice( final String id,
            final List< ? extends T > data,
            final IChoiceRenderer< ? super T > renderer ) {
        super( id, data, renderer );
    }

    /**
     * {@inheritDoc}
     */
    protected CharSequence getDefaultChoice( final String selected ) {
        if ( defaultChoice ) {
            return "";
        }
        return super.getDefaultChoice( selected );
    }

    /**
     * Checks if is default choice.
     *
     * @return true, if is default choice
     */
    public boolean isDefaultChoice() {
        return defaultChoice;
    }

    /**
     * {@inheritDoc}
     */
    protected boolean localizeDisplayValues() {
        return true;
    }

    /**
     * Sets the default choice.
     *
     * @param defaultChoice the new default choice
     */
    public void setDefaultChoice( final boolean defaultChoice ) {
        this.defaultChoice = defaultChoice;
    }

}
