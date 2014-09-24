package org.jaulp.wicket.components.socialnet.twitter.share;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.jaulp.wicket.base.BasePanel;

public class TwitterSharePanel extends BasePanel<TwitterShareModel> {

	private static final long serialVersionUID = 1L;

	public TwitterSharePanel(String id) {
		this(id, null);
	}

	public TwitterSharePanel(String id, IModel<TwitterShareModel> model) {
		super(id, model);
		ExternalLink twitterShareLink = new ExternalLink("shareUrl", model.getObject().getShareUrl());
		if(model.getObject().getDataUrl() != null) {
			twitterShareLink.add(new AttributeModifier("data-url", model.getObject().getDataUrl()));
		}
		if(model.getObject().getVia() != null) {
			twitterShareLink.add(new AttributeModifier("data-via",model.getObject().getVia()));
		}
		if(model.getObject().getCounturl() != null) {
			twitterShareLink.add(new AttributeModifier("data-counturl",model.getObject().getCounturl()));
		}
		twitterShareLink.add(new AttributeModifier("data-show-count",model.getObject().getShowCount().toString()));
		
		if(model.getObject().getCountAlign() != null) {
			// if the count should go at the top of the twitter icon use "vertical"...
			// twitterShareLink.add(new AttributeModifier("data-count", "vertical"));
			twitterShareLink.add(new AttributeModifier("data-count", model.getObject().getCountAlign()));
		}
		add(twitterShareLink);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		PackageResourceReference resourceReference = 
	            new PackageResourceReference(getClass(), "widgets.js");
		response.render(JavaScriptHeaderItem.forReference(resourceReference, "twitterShare"));
	}
}
