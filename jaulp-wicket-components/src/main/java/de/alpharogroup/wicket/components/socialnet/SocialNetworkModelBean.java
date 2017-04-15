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
package de.alpharogroup.wicket.components.socialnet;

import java.io.Serializable;

import de.alpharogroup.wicket.components.socialnet.fb.like.and.share.FacebookLikeAndShareModelBean;
import de.alpharogroup.wicket.components.socialnet.googleplus.share.GooglePlusShareModelBean;
import de.alpharogroup.wicket.components.socialnet.twitter.follow.TwitterFollowModelBean;
import de.alpharogroup.wicket.components.socialnet.twitter.share.TwitterShareModelBean;
import lombok.Builder;
import lombok.Data;

/**
 * Model bean for the page SocialNetworks data.
 */
@Data
@Builder
public class SocialNetworkModelBean implements Serializable
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The model bean for the google plus share. */
	private GooglePlusShareModelBean googlePlusShareModelBean;

	/** The model bean for the twitter share. */
	private TwitterShareModelBean twitterShareModelBean;

	/** The model bean for the twitter follow. */
	private TwitterFollowModelBean twitterFollowModelBean;

	/** The model bean for the facebook like and share. */
	private FacebookLikeAndShareModelBean facebookLikeAndShareModelBean;
}
