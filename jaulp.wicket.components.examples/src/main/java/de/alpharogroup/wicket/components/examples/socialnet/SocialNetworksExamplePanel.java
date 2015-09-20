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
package de.alpharogroup.wicket.components.examples.socialnet;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.locale.LocaleUtils;
import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.base.util.url.WicketUrlExtensions;
import de.alpharogroup.wicket.components.socialnet.fb.like.and.share.FacebookLikeAndShareModelBean;
import de.alpharogroup.wicket.components.socialnet.fb.like.and.share.FacebookLikeAndSharePanel;
import de.alpharogroup.wicket.components.socialnet.googleplus.share.GooglePlusShareModelBean;
import de.alpharogroup.wicket.components.socialnet.googleplus.share.GooglePlusSharePanel;
import de.alpharogroup.wicket.components.socialnet.twitter.follow.TwitterFollowModelBean;
import de.alpharogroup.wicket.components.socialnet.twitter.follow.TwitterFollowPanel;
import de.alpharogroup.wicket.components.socialnet.twitter.share.TwitterShareModelBean;
import de.alpharogroup.wicket.components.socialnet.twitter.share.TwitterSharePanel;

/**
 * Panel for social networks like fb, twitter, google+ etc.
 */
public class SocialNetworksExamplePanel extends BasePanel<SocialNetworkBean>
{
	private static final long serialVersionUID = 1L;

	public SocialNetworksExamplePanel(final String id)
	{
		super(id);
	}

	protected Component newFacebookLikeAndSharePanel(final String id)
	{
		final FacebookLikeAndShareModelBean model = new FacebookLikeAndShareModelBean.Builder().build();
		final FacebookLikeAndSharePanel facebookLikeAndSharePanel = new FacebookLikeAndSharePanel(
			id, Model.of(model));
		return facebookLikeAndSharePanel;
	}

	protected Component newGooglePlusSharePanel(final String id)
	{
		final IModel<GooglePlusShareModelBean> model = new GooglePlusShareModelBean.Builder()
			.scriptSrc("https://apis.google.com/js/platform.js")
			.locale(LocaleUtils.getLocaleFileSuffix(Session.get().getLocale(), false, false, false))
			.cssClass("g-plusone").dataAnnotation("inline").dataWith("300")
			.dataHref(WicketUrlExtensions.absoluteUrlFor(this.getPage().getClass(), false)).build()
			.toModel();
		return new GooglePlusSharePanel(id, model);
	}

	protected Component newTwitterFollowPanel(final String id)
	{
		final String username = "jaulp.wicket";
		return new TwitterFollowPanel(id, new TwitterFollowModelBean.Builder().username(username)
			.urlPrefix("https://twitter.com/").url("https://twitter.com/" + username).build()
			.toModel());
	}

	protected Component newTwitterSharePanel(final String id)
	{
		final String dataUrl = "http://www.jaulp-wicket-components.com";
		final IModel<TwitterShareModelBean> model = new TwitterShareModelBean.Builder()
			.shareUrl("https://twitter.com/share").dataUrl(dataUrl).via(dataUrl).counturl(dataUrl)
			.build().toModel();
		return new TwitterSharePanel(id, model);
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(newFacebookLikeAndSharePanel("facebookLikeAndSharePanel"));
		add(newTwitterSharePanel("twitterSharePanel"));
		add(newTwitterFollowPanel("twitterFollowPanel"));
		add(newGooglePlusSharePanel("googleplusSharePanel"));

		add(new Label("messageSourceLabel", ResourceModelFactory.newResourceModel(ResourceBundleKey
			.builder().key("foo.bar.bla").build())));
	}
}
