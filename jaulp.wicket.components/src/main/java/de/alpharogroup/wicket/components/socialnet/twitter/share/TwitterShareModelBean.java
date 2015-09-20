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
package de.alpharogroup.wicket.components.socialnet.twitter.share;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * The Class {@link TwitterShareModelBean}.
 */
public class TwitterShareModelBean implements Serializable
{

	/**
	 * The Class {@link Builder}.
	 */
	public static class Builder
	{

		/** The share url. */
		private String shareUrl;

		/** The data url. */
		private String dataUrl;

		/** The via. */
		private String via;

		/** The counturl. */
		private String counturl;

		/** The show count. */
		private Boolean showCount = Boolean.FALSE;

		/** The count align. */
		private String countAlign;

		/**
		 * Builds the.
		 *
		 * @return the twitter share model bean
		 */
		public TwitterShareModelBean build()
		{
			return new TwitterShareModelBean(this);
		}

		/**
		 * Count align.
		 *
		 * @param countAlign
		 *            the count align
		 * @return the builder
		 */
		public Builder countAlign(final String countAlign)
		{
			this.countAlign = countAlign;
			return this;
		}

		/**
		 * Counturl.
		 *
		 * @param counturl
		 *            the counturl
		 * @return the builder
		 */
		public Builder counturl(final String counturl)
		{
			this.counturl = counturl;
			return this;
		}

		/**
		 * Data url.
		 *
		 * @param dataUrl
		 *            the data url
		 * @return the builder
		 */
		public Builder dataUrl(final String dataUrl)
		{
			this.dataUrl = dataUrl;
			return this;
		}

		/**
		 * Share url.
		 *
		 * @param shareUrl
		 *            the share url
		 * @return the builder
		 */
		public Builder shareUrl(final String shareUrl)
		{
			this.shareUrl = shareUrl;
			return this;
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
		 * Via.
		 *
		 * @param via
		 *            the via
		 * @return the builder
		 */
		public Builder via(final String via)
		{
			this.via = via;
			return this;
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The share url. */
	private final String shareUrl;

	/** The data url. */
	private final String dataUrl;

	/** The via. */
	private final String via;

	/** The counturl. */
	private final String counturl;

	/** The show count. */
	private Boolean showCount = Boolean.FALSE;

	/** The count align. */
	private final String countAlign;

	/**
	 * Instantiates a new {@link TwitterShareModelBean}.
	 *
	 * @param builder
	 *            the builder
	 */
	private TwitterShareModelBean(final Builder builder)
	{
		this.shareUrl = builder.shareUrl;
		this.dataUrl = builder.dataUrl;
		this.via = builder.via;
		this.counturl = builder.counturl;
		this.showCount = builder.showCount;
		this.countAlign = builder.countAlign;
	}

	/**
	 * Gets the count align.
	 *
	 * @return the count align
	 */
	public String getCountAlign()
	{
		return countAlign;
	}

	/**
	 * Gets the counturl.
	 *
	 * @return the counturl
	 */
	public String getCounturl()
	{
		return counturl;
	}

	/**
	 * Gets the data url.
	 *
	 * @return the data url
	 */
	public String getDataUrl()
	{
		return dataUrl;
	}

	/**
	 * Gets the share url.
	 *
	 * @return the share url
	 */
	public String getShareUrl()
	{
		return shareUrl;
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
	 * Gets the via.
	 *
	 * @return the via
	 */
	public String getVia()
	{
		return via;
	}

	/**
	 * To model.
	 *
	 * @return the i model
	 */
	public IModel<TwitterShareModelBean> toModel()
	{
		return Model.of(this);
	}
}
