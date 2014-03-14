package org.jaulp.wicket.base;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.jaulp.wicket.base.util.WicketComponentUtils;

/**
 * The Class BasePanel.
 */
public abstract class BasePanel<T> extends GenericPanel<T> {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new base panel.
     *
     * @param id the id
     */
    public BasePanel( final String id ) {
        this( id, null );
    }

    /**
     * Instantiates a new base panel.
     *
     * @param id the id
     * @param model the model
     */
    public BasePanel( final String id, final IModel< T > model ) {
        super( id, model );
    }

    /**
     * {@inheritDoc}
     */
    public void renderHead(IHeaderResponse response) {
    	super.renderHead(response);
    	WicketComponentUtils.renderHeaderResponse(response, this.getClass());
    }

}