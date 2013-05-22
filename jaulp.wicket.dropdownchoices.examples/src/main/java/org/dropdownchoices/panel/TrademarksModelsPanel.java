/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * This program is open source software; you can redistribute it and/or modify
 * it under the terms of the Apache License V2.0 as published by
 * the Apache Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 */
package org.dropdownchoices.panel;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.jaulp.wicket.components.i18n.dropdownchoice.panels.TwoDropDownChoicesPanel;
import org.jaulp.wicket.model.dropdownchoices.StringTwoDropDownChoicesModel;

/**
 * The Class TrademarksModelsPanel.
 *
 * @author admin
 */
public class TrademarksModelsPanel extends TwoDropDownChoicesPanel {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new trademarks models panel.
	 *
	 * @param id the id
	 * @param stringTwoDropDownChoicesModel the string two drop down choices model
	 * @param rootRenderer the root renderer
	 * @param childRenderer the child renderer
	 */
	public TrademarksModelsPanel(String id,
			StringTwoDropDownChoicesModel stringTwoDropDownChoicesModel,
			IChoiceRenderer<String> rootRenderer,
			IChoiceRenderer<String> childRenderer) {
		super(id, stringTwoDropDownChoicesModel, rootRenderer, childRenderer);
	}

}

