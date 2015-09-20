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

/**
 * The Class {@link GooglePlusShareModelBean}.
 */
public class GooglePlusShareModelBean implements Serializable
{

	/**
	 * The Class {@link Builder}.
	 */
	public static class Builder
	{

		/** The css class. */
		private String cssClass;

		/** The data annotation. */
		private String dataAnnotation;

		/** The data href. */
		private String dataHref;

		/** The data with. */
		private String dataWith;

		/** The locale. */
		private String locale;

		/** The script src. */
		private String scriptSrc;

		/**
		 * Builds the.
		 *
		 * @return the google plus share model bean
		 */
		public GooglePlusShareModelBean build()
		{
			return new GooglePlusShareModelBean(this);
		}

		/**
		 * Css class.
		 *
		 * @param cssClass
		 *            the css class
		 * @return the builder
		 */
		public Builder cssClass(final String cssClass)
		{
			this.cssClass = cssClass;
			return this;
		}

		/**
		 * Data annotation.
		 *
		 * @param dataAnnotation
		 *            the data annotation
		 * @return the builder
		 */
		public Builder dataAnnotation(final String dataAnnotation)
		{
			this.dataAnnotation = dataAnnotation;
			return this;
		}

		/**
		 * Data href.
		 *
		 * @param dataHref
		 *            the data href
		 * @return the builder
		 */
		public Builder dataHref(final String dataHref)
		{
			this.dataHref = dataHref;
			return this;
		}

		/**
		 * Data with.
		 *
		 * @param dataWith
		 *            the data with
		 * @return the builder
		 */
		public Builder dataWith(final String dataWith)
		{
			this.dataWith = dataWith;
			return this;
		}

		/**
		 * Locale.
		 *
		 * @param locale
		 *            the locale
		 * @return the builder
		 */
		public Builder locale(final String locale)
		{
			this.locale = locale;
			return this;
		}

		/**
		 * Script src.
		 *
		 * @param scriptSrc
		 *            the script src
		 * @return the builder
		 */
		public Builder scriptSrc(final String scriptSrc)
		{
			this.scriptSrc = scriptSrc;
			return this;
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The css class. */
	private final String cssClass;

	/** The data annotation. */
	private final String dataAnnotation;

	/** The data href. */
	private final String dataHref;

	/** The data with. */
	private final String dataWith;

	/** The locale. */
	private final String locale;

	/** The script src. */
	private final String scriptSrc;

	/**
	 * Instantiates a new {@link GooglePlusShareModelBean}.
	 *
	 * @param builder
	 *            the builder
	 */
	private GooglePlusShareModelBean(final Builder builder)
	{
		this.cssClass = builder.cssClass;
		this.dataAnnotation = builder.dataAnnotation;
		this.dataWith = builder.dataWith;
		this.dataHref = builder.dataHref;
		this.scriptSrc = builder.scriptSrc;
		this.locale = builder.locale;
	}

	/**
	 * Gets the css class.
	 *
	 * @return the css class
	 */
	public String getCssClass()
	{
		return cssClass;
	}

	/**
	 * Gets the data annotation.
	 *
	 * @return the data annotation
	 */
	public String getDataAnnotation()
	{
		return dataAnnotation;
	}

	/**
	 * Gets the data href.
	 *
	 * @return the data href
	 */
	public String getDataHref()
	{
		return dataHref;
	}

	/**
	 * Gets the data with.
	 *
	 * @return the data with
	 */
	public String getDataWith()
	{
		return dataWith;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public String getLocale()
	{
		return locale;
	}

	/**
	 * Gets the script src.
	 *
	 * @return the script src
	 */
	public String getScriptSrc()
	{
		return scriptSrc;
	}

	/**
	 * To model.
	 *
	 * @return the i model
	 */
	public IModel<GooglePlusShareModelBean> toModel()
	{
		return Model.of(this);
	}
}