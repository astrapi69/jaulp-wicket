package org.jaulp.wicket.dialogs.examples.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class ModalDialogWithStylePanel extends Panel {

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
    private static ResourceReference CSS = new PackageResourceReference(
            CustomModalWindow.class, "cart_global.css");

	public ModalDialogWithStylePanel(String id) {
		super(id);
		final ModalWindow modal = new ModalWindow("modal");
		modal.setCssClassName("w_vegas");
	    modal.setTitle("Trivial Modal");

	    AjaxLink<Void> modalLink = new AjaxLink<Void>("modalLink") {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void onClick(AjaxRequestTarget target) {

	            target.appendJavaScript("var originalStyle = $('.wicket-modal').attr('style');"
	                    + "$('.wicket-modal').attr('style', originalStyle + 'opacity: 0.5;');");
	        }
	    };
	    Fragment modalFragment = new Fragment(modal.getContentId(), "modalContent", this);
	    modalFragment.add(modalLink);
	    modal.setContent(modalFragment);

	    add(modal);
	    add(new AjaxLink<Void>("openModal") {
	        private static final long serialVersionUID = 1L;

	        @Override
	        public void onClick(AjaxRequestTarget target) {
	            modal.show(target);
	        }
	    });
	}   
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		CssReferenceHeaderItem headerItem = CssHeaderItem
				.forReference(CSS);
		response.render(headerItem);
	}

}
