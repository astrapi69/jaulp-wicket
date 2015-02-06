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
package de.alpharogroup.wicket.components.socialnet.googleplus.share;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class GooglePlusShareModel implements Serializable
{

	private static final long serialVersionUID = 1L;

	private final String cssClass;

	private final String dataAnnotation;

	private final String dataWith;

	private final String dataHref;

	private final String scriptSrc;

	private final String locale;

	public String getCssClass()
	{
		return cssClass;
	}

	public String getDataAnnotation()
	{
		return dataAnnotation;
	}

	public String getDataWith()
	{
		return dataWith;
	}

	public String getDataHref()
	{
		return dataHref;
	}

	public String getScriptSrc()
	{
		return scriptSrc;
	}

	public String getLocale()
	{
		return locale;
	}

	public IModel<GooglePlusShareModel> toModel()
	{
		return Model.of(this);
	}

	public static class Builder
	{
		private String cssClass;
		private String dataAnnotation;
		private String dataWith;
		private String dataHref;
		private String scriptSrc;
		private String locale;

		public Builder cssClass(String cssClass)
		{
			this.cssClass = cssClass;
			return this;
		}

		public Builder dataAnnotation(String dataAnnotation)
		{
			this.dataAnnotation = dataAnnotation;
			return this;
		}

		public Builder dataWith(String dataWith)
		{
			this.dataWith = dataWith;
			return this;
		}

		public Builder dataHref(String dataHref)
		{
			this.dataHref = dataHref;
			return this;
		}

		public Builder scriptSrc(String scriptSrc)
		{
			this.scriptSrc = scriptSrc;
			return this;
		}

		public Builder locale(String locale)
		{
			this.locale = locale;
			return this;
		}

		public GooglePlusShareModel build()
		{
			return new GooglePlusShareModel(this);
		}
	}

	private GooglePlusShareModel(Builder builder)
	{
		this.cssClass = builder.cssClass;
		this.dataAnnotation = builder.dataAnnotation;
		this.dataWith = builder.dataWith;
		this.dataHref = builder.dataHref;
		this.scriptSrc = builder.scriptSrc;
		this.locale = builder.locale;
	}
}