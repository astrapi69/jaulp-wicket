package org.jaulp.wicket.dialogs.examples;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.jaulp.wicket.dialogs.examples.panel.UploadFilePanel;

/**
 * @author admin
 */
public class UploadPage extends WebPage {

	public UploadPage(PageParameters parameters) {
		super(parameters);
		final ModalWindow uploadFileDialog = new ModalWindow("uploadFileDialog");

		add(uploadFileDialog);

		final UploadFilePanel uploadFilePanel = new UploadFilePanel("content");
		uploadFileDialog.setContent(uploadFilePanel);

		add(new AjaxLink<Void>("showUpdoadFileDialog"){
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				uploadFileDialog.show(target);
			}
		});
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


}

