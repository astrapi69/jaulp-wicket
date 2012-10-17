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
package org.jaulp.wicket.dialogs.examples;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * The Class RepliesandnotesPanel.
 */
public class RepliesandnotesPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new repliesandnotes panel.
	 *
	 * @param id the id
	 * @param repliesandnotes the repliesandnotes
	 */
	public RepliesandnotesPanel(String id, final MessageBean repliesandnotes) {
		super(id);
		add(new Label("label", repliesandnotes.getMessageContent()));
	}


}
