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
package org.jaulp.wicket.components.socialnet.fb.like.and.share;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class FacebookLikeAndShareModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean dataShare;

	private Integer dataWith;

	private Boolean dataShowFaces;

	public Boolean getDataShare() {
		return dataShare;
	}

	public Integer getDataWith() {
		return dataWith;
	}

	public Boolean getDataShowFaces() {
		return dataShowFaces;
	}
	
	public IModel<FacebookLikeAndShareModel> toModel() {
		return Model.of(this);
	}

	public static class Builder {
		private Boolean dataShare = Boolean.TRUE;
		private Integer dataWith = 450;
		private Boolean dataShowFaces = Boolean.TRUE;

		public Builder dataShare(Boolean dataShare) {
			this.dataShare = dataShare;
			return this;
		}

		public Builder dataWith(Integer dataWith) {
			this.dataWith = dataWith;
			return this;
		}

		public Builder dataShowFaces(Boolean dataShowFaces) {
			this.dataShowFaces = dataShowFaces;
			return this;
		}

		public FacebookLikeAndShareModel build() {
			return new FacebookLikeAndShareModel(this);
		}
	}

	private FacebookLikeAndShareModel(Builder builder) {
		this.dataShare = builder.dataShare;
		this.dataWith = builder.dataWith;
		this.dataShowFaces = builder.dataShowFaces;
	}
}
