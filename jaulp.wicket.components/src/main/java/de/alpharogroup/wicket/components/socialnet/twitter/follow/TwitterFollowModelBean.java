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
package de.alpharogroup.wicket.components.socialnet.twitter.follow;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The Class {@link TwitterFollowModelBean}.
 */
public class TwitterFollowModelBean implements Serializable
{

	/**
	 * The Class {@link Builder}.
	 */
	public static class Builder
	{

		/** The username. */
		private String username;

		/** The url prefix. */
		private String urlPrefix;

		/** The show count. */
		private Boolean showCount = Boolean.FALSE;

		/** The url. */
		private String url;

		/**
		 * Builds the.
		 *
		 * @return the twitter follow model bean
		 */
		public TwitterFollowModelBean build()
		{
			return new TwitterFollowModelBean(this);
		}

		/**
		 * Show count.
		 *
		 * @param showCount
		 *            the show count
		 * @return the builder
		 */
		public Builder showCount(final Boolean showCount)
		{
			this.showCount = showCount;
			return this;
		}

		/**
		 * Url.
		 *
		 * @param url
		 *            the url
		 * @return the builder
		 */
		public Builder url(final String url)
		{
			this.url = url;
			return this;
		}

		/**
		 * Url prefix.
		 *
		 * @param urlPrefix
		 *            the url prefix
		 * @return the builder
		 */
		public Builder urlPrefix(final String urlPrefix)
		{
			this.urlPrefix = urlPrefix;
			return this;
		}

		/**
		 * Username.
		 *
		 * @param username
		 *            the username
		 * @return the builder
		 */
		public Builder username(final String username)
		{
			this.username = username;
			return this;
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The username. */
	private final String username;

	/** The url prefix. */
	private final String urlPrefix;

	/** The show count. */
	private Boolean showCount = Boolean.FALSE;

	/** The url. */
	private final String url;

	/**
	 * Instantiates a new {@link TwitterFollowModelBean}.
	 *
	 * @param builder
	 *            the builder
	 */
	private TwitterFollowModelBean(final Builder builder)
	{
		this.username = builder.username;
		this.urlPrefix = builder.urlPrefix;
		this.showCount = builder.showCount;
		this.url = builder.url;
	}

	/**
	 * Gets the show count.
	 *
	 * @return the show count
	 */
	public Boolean getShowCount()
	{
		return showCount;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Gets the url prefix.
	 *
	 * @return the url prefix
	 */
	public String getUrlPrefix()
	{
		return urlPrefix;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * To model.
	 *
	 * @return the i model
	 */
	public IModel<TwitterFollowModelBean> toModel()
	{
		return Model.of(this);
	}
}
