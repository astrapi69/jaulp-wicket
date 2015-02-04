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

public class TwitterFollowModel implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String username;
	private String urlPrefix;
	private Boolean showCount = Boolean.FALSE;
	private String url;

	public String getUsername()
	{
		return username;
	}

	public String getUrlPrefix()
	{
		return urlPrefix;
	}

	public Boolean getShowCount()
	{
		return showCount;
	}

	public String getUrl()
	{
		return url;
	}

	public IModel<TwitterFollowModel> toModel()
	{
		return Model.of(this);
	}

	public static class Builder
	{
		private String username;
		private String urlPrefix;
		private Boolean showCount = Boolean.FALSE;
		private String url;

		public Builder username(String username)
		{
			this.username = username;
			return this;
		}

		public Builder urlPrefix(String urlPrefix)
		{
			this.urlPrefix = urlPrefix;
			return this;
		}

		public Builder showCount(Boolean showCount)
		{
			this.showCount = showCount;
			return this;
		}

		public Builder url(String url)
		{
			this.url = url;
			return this;
		}

		public TwitterFollowModel build()
		{
			return new TwitterFollowModel(this);
		}
	}

	private TwitterFollowModel(Builder builder)
	{
		this.username = builder.username;
		this.urlPrefix = builder.urlPrefix;
		this.showCount = builder.showCount;
		this.url = builder.url;
	}
}
