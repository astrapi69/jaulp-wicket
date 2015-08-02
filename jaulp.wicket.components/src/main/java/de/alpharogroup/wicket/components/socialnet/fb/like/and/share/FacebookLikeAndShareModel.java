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
package de.alpharogroup.wicket.components.socialnet.fb.like.and.share;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class FacebookLikeAndShareModel implements Serializable
{

	public static class Builder
	{
		private Boolean dataShare = Boolean.TRUE;
		private Integer dataWith = 450;
		private Boolean dataShowFaces = Boolean.TRUE;

		public FacebookLikeAndShareModel build()
		{
			return new FacebookLikeAndShareModel(this);
		}

		public Builder dataShare(final Boolean dataShare)
		{
			this.dataShare = dataShare;
			return this;
		}

		public Builder dataShowFaces(final Boolean dataShowFaces)
		{
			this.dataShowFaces = dataShowFaces;
			return this;
		}

		public Builder dataWith(final Integer dataWith)
		{
			this.dataWith = dataWith;
			return this;
		}
	}

	private static final long serialVersionUID = 1L;

	private final Boolean dataShare;

	private final Integer dataWith;

	private final Boolean dataShowFaces;

	public FacebookLikeAndShareModel(final Builder builder)
	{
		this.dataShare = builder.dataShare;
		this.dataWith = builder.dataWith;
		this.dataShowFaces = builder.dataShowFaces;
	}

	public Boolean getDataShare()
	{
		return dataShare;
	}

	public Boolean getDataShowFaces()
	{
		return dataShowFaces;
	}

	public Integer getDataWith()
	{
		return dataWith;
	}

	public IModel<FacebookLikeAndShareModel> toModel()
	{
		return Model.of(this);
	}
}
