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

/**
 * The Class {@link FacebookLikeAndShareModelBean}.
 */
public class FacebookLikeAndShareModelBean implements Serializable
{

	/**
	 * The Class {@link Builder}.
	 */
	public static class Builder
	{

		/** The data share. */
		private Boolean dataShare = Boolean.TRUE;

		/** The data with. */
		private Integer dataWith = 450;

		/** The data show faces. */
		private Boolean dataShowFaces = Boolean.TRUE;

		/**
		 * Builds the.
		 *
		 * @return the facebook like and share model bean
		 */
		public FacebookLikeAndShareModelBean build()
		{
			return new FacebookLikeAndShareModelBean(this);
		}

		/**
		 * Data share.
		 *
		 * @param dataShare
		 *            the data share
		 * @return the builder
		 */
		public Builder dataShare(final Boolean dataShare)
		{
			this.dataShare = dataShare;
			return this;
		}

		/**
		 * Data show faces.
		 *
		 * @param dataShowFaces
		 *            the data show faces
		 * @return the builder
		 */
		public Builder dataShowFaces(final Boolean dataShowFaces)
		{
			this.dataShowFaces = dataShowFaces;
			return this;
		}

		/**
		 * Data with.
		 *
		 * @param dataWith
		 *            the data with
		 * @return the builder
		 */
		public Builder dataWith(final Integer dataWith)
		{
			this.dataWith = dataWith;
			return this;
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data share. */
	private final Boolean dataShare;

	/** The data with. */
	private final Integer dataWith;

	/** The data show faces. */
	private final Boolean dataShowFaces;

	/**
	 * Instantiates a new {@link FacebookLikeAndShareModelBean}.
	 *
	 * @param builder
	 *            the builder
	 */
	public FacebookLikeAndShareModelBean(final Builder builder)
	{
		this.dataShare = builder.dataShare;
		this.dataWith = builder.dataWith;
		this.dataShowFaces = builder.dataShowFaces;
	}

	/**
	 * Gets the data share.
	 *
	 * @return the data share
	 */
	public Boolean getDataShare()
	{
		return dataShare;
	}

	/**
	 * Gets the data show faces.
	 *
	 * @return the data show faces
	 */
	public Boolean getDataShowFaces()
	{
		return dataShowFaces;
	}

	/**
	 * Gets the data with.
	 *
	 * @return the data with
	 */
	public Integer getDataWith()
	{
		return dataWith;
	}

	/**
	 * To model.
	 *
	 * @return the i model
	 */
	public IModel<FacebookLikeAndShareModelBean> toModel()
	{
		return Model.of(this);
	}
}
