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

public class TwitterShareModel implements Serializable
{

	public static class Builder
	{
		private String shareUrl;
		private String dataUrl;
		private String via;
		private String counturl;
		private Boolean showCount = Boolean.FALSE;
		private String countAlign;

		public TwitterShareModel build()
		{
			return new TwitterShareModel(this);
		}

		public Builder countAlign(final String countAlign)
		{
			this.countAlign = countAlign;
			return this;
		}

		public Builder counturl(final String counturl)
		{
			this.counturl = counturl;
			return this;
		}

		public Builder dataUrl(final String dataUrl)
		{
			this.dataUrl = dataUrl;
			return this;
		}

		public Builder shareUrl(final String shareUrl)
		{
			this.shareUrl = shareUrl;
			return this;
		}

		public Builder showCount(final Boolean showCount)
		{
			this.showCount = showCount;
			return this;
		}

		public Builder via(final String via)
		{
			this.via = via;
			return this;
		}
	}

	private static final long serialVersionUID = 1L;

	private final String shareUrl;

	private final String dataUrl;

	private final String via;

	private final String counturl;

	private Boolean showCount = Boolean.FALSE;

	private final String countAlign;

	private TwitterShareModel(final Builder builder)
	{
		this.shareUrl = builder.shareUrl;
		this.dataUrl = builder.dataUrl;
		this.via = builder.via;
		this.counturl = builder.counturl;
		this.showCount = builder.showCount;
		this.countAlign = builder.countAlign;
	}

	public String getCountAlign()
	{
		return countAlign;
	}

	public String getCounturl()
	{
		return counturl;
	}

	public String getDataUrl()
	{
		return dataUrl;
	}

	public String getShareUrl()
	{
		return shareUrl;
	}

	public Boolean getShowCount()
	{
		return showCount;
	}

	public String getVia()
	{
		return via;
	}

	public IModel<TwitterShareModel> toModel()
	{
		return Model.of(this);
	}
}
