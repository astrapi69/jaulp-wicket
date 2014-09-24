package org.jaulp.wicket.components.socialnet.twitter.follow;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.jaulp.wicket.base.BasePanel;

public class TwitterFollowPanel extends BasePanel<TwitterFollowModel> {
	private static final long serialVersionUID = 1L;

	public TwitterFollowPanel(String id) {
		this(id, null);
	}

	public TwitterFollowPanel(String id, IModel<TwitterFollowModel> model) {
		super(id, model);
		ExternalLink twitterFollowLink = new ExternalLink("url", model.getObject().getUrl());
		twitterFollowLink.add(new AttributeModifier("data-show-count", model.getObject().getShowCount().toString()));
		add(twitterFollowLink);
		Label twitterNameLabel = new Label("twitterNameLabel", model.getObject().getUsername());
		twitterFollowLink.add(twitterNameLabel);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		PackageResourceReference resourceReference = 
	            new PackageResourceReference(getClass(), "follow.js");
		response.render(JavaScriptHeaderItem.forReference(resourceReference, "twitterFollow"));
	}

}
