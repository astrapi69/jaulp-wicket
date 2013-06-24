package org.jaulp.wicket.components.img;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.jaulp.wicket.base.utils.WicketComponentUtils;

/**
 * The Class WicketImage.
 * 
 * @author Asterios Raptis
 */
public class WicketImage extends WebComponent {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new wicket image.
	 * 
	 * @param id
	 *            the id
	 */
	public WicketImage(final String id) {
		super(id);
	}

	/**
	 * Instantiates a new wicket image.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public WicketImage(final String id, final IModel<?> model) {
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onComponentTag(final ComponentTag tag) {
		checkComponentTag(tag, "img");
		super.onComponentTag(tag);
		final String modelObjectAsString = getDefaultModelObjectAsString();
		final String contextPath = WicketComponentUtils.getContextPath(((WebApplication) getApplication()));
		final String imagePath = contextPath + modelObjectAsString;
		tag.put("src", imagePath);
	}
}
