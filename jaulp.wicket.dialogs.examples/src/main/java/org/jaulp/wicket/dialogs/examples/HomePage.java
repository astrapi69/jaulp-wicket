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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.core.util.lang.WicketObjects;
import org.jaulp.wicket.dialogs.ajax.modal.BaseModalWindow;

/**
 * Homepage.
 */
public class HomePage extends WebPage {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters) {

		final WebMarkupContainer wmc = new WebMarkupContainer("comments");
		wmc.setOutputMarkupId(true);


		final List<MessageBean> noteList = new ArrayList<MessageBean>();
		final MessageBean messageBean = new MessageBean();
		messageBean.setMessageContent("hello");
		final ModalWindow modalWindow = (ModalWindow) new BaseModalWindow<MessageBean>(
				"baseModalWindow", "Title", 350, 160,
				new CompoundPropertyModel<MessageBean>(messageBean)) {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onCancel(final AjaxRequestTarget target) {
				target.add(wmc);
				close(target);
			}

			@Override
			public void onSelect(final AjaxRequestTarget target,
					final MessageBean object) {
				MessageBean clone = (MessageBean) WicketObjects.cloneObject(object);
				noteList.add(clone);
				// Clear the content from textarea in the dialog.
				object.setMessageContent("");
				target.add(wmc);
				close(target);
			}

		};
		modalWindow.setResizable(false);
		add(modalWindow);

		final AjaxLink<String> linkToModalWindow = new AjaxLink<String>(
				"linkToModalWindow") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				modalWindow.show(target);
			}
		};
		// Add the WebMarkupContainer...
		add(wmc);

		final Label linkToModalWindowLabel = new Label(
				"linkToModalWindowLabel", "show modal dialog");
		linkToModalWindow.add(linkToModalWindowLabel);
		// The AjaxLink to open the modal window
		add(linkToModalWindow);
		// here we must set the message content from the bean in a repeater...
        final ListView< MessageBean > repliesAndNotesListView = new ListView< MessageBean >(
                "repliesAndNotesListView", noteList ) {
            /**
             * The serialVersionUID.
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem( final ListItem< MessageBean > item ) {
                final MessageBean repliesandnotes = item.getModelObject();
                item.add( new RepliesandnotesPanel( "repliesandnotesPanel",
                        repliesandnotes ) );

            }
        };
        repliesAndNotesListView.setVisible( true );
        wmc.add( repliesAndNotesListView );

        @SuppressWarnings("rawtypes")
		Link showUploadPage = new Link("showUploadPage") {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new UploadPage(getPageParameters()));
			}

        };
        add(showUploadPage);
	}
}
