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

import java.io.Serializable;

/**
 * The Class MessageBean.
 */
public class MessageBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message content. */
	String messageContent;

	/**
	 * Gets the message content.
	 *
	 * @return the message content
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * Sets the message content.
	 *
	 * @param messageContent the new message content
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	/**
	 * Instantiates a new message bean.
	 */
	public MessageBean() {
		super();
	}


}
