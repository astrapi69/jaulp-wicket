package org.jaulp.wicket.dialogs.examples.panel;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class CustomModalWindow extends ModalWindow {

	private static final long serialVersionUID = 1L;

    private static ResourceReference CSS = new PackageResourceReference(
            CustomModalWindow.class, "custom-modal.css");

    /**
     * Creates a new modal window component.
     * 
     * @param id
     *            Id of component
     */
    public CustomModalWindow(final String id) {
        super(id);
    }

    /**
     * Creates a new modal window component.
     * 
     * @param id
     *            Id of component
     * @param model
     *            Model
     */
    public CustomModalWindow(final String id, final IModel<?> model) {
        super(id, model);
    }

    @Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		CssReferenceHeaderItem headerItem = CssHeaderItem
				.forReference(CSS);
		response.render(headerItem);
	}
    
    

}