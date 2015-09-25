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
package de.alpharogroup.wicket.dialogs.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.core.util.lang.WicketObjects;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.alpharogroup.wicket.dialogs.ajax.modal.BaseModalWindow;
import de.alpharogroup.wicket.dialogs.examples.panel.ModalDialogWithStylePanel;

/**
 * Homepage.
 */
public class HomePage extends WebPage
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public HomePage(final PageParameters parameters)
	{

		final WebMarkupContainer wmc = new WebMarkupContainer("comments");
		wmc.setOutputMarkupId(true);


		final List<MessageBean> noteList = new ArrayList<MessageBean>();
		final MessageBean messageBean = new MessageBean();
		messageBean.setMessageContent("hello");
		final IModel<MessageBean> dialogModel = new CompoundPropertyModel<MessageBean>(messageBean);
		final ModalWindow modalWindow = new BaseModalWindow<MessageBean>("baseModalWindow",
			dialogModel, "Title", 350, 160)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onCancel(final AjaxRequestTarget target)
			{
				target.add(wmc);
				close(target);
			}

			@Override
			public void onSelect(final AjaxRequestTarget target, final MessageBean object)
			{
				final MessageBean clone = (MessageBean)WicketObjects.cloneObject(object);
				noteList.add(clone);
				// Clear the content from textarea in the dialog.
				object.setMessageContent("");
				target.add(wmc);
				close(target);
			}

		};
		modalWindow.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		modalWindow.setResizable(false);
		add(modalWindow);

		final AjaxLink<String> linkToModalWindow = new AjaxLink<String>("linkToModalWindow")
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				modalWindow.show(target);
			}
		};
		// Add the WebMarkupContainer...
		add(wmc);

		final Label linkToModalWindowLabel = new Label("linkToModalWindowLabel",
			"show modal dialog");
		linkToModalWindow.add(linkToModalWindowLabel);
		// The AjaxLink to open the modal window
		add(linkToModalWindow);
		// here we have to set the message content from the bean in a repeater...
		final ListView<MessageBean> repliesAndNotesListView = new ListView<MessageBean>(
			"repliesAndNotesListView", noteList)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<MessageBean> item)
			{
				final MessageBean repliesandnotes = item.getModelObject();
				item.add(new RepliesandnotesPanel("repliesandnotesPanel", repliesandnotes));

			}
		};
		repliesAndNotesListView.setVisible(true);
		wmc.add(repliesAndNotesListView);

		@SuppressWarnings("rawtypes")
		final Link showUploadPage = new Link("showUploadPage")
		{

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(new UploadPage(getPageParameters()));
			}

		};
		add(showUploadPage);

		add(new ModalDialogWithStylePanel("modalDialogWithStylePanel", Model.of("bla")));

	}
}
