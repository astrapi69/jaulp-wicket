package org.jaulp.wicket.components.socialnet.googleplus.share;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class GooglePlusShareModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cssClass;

	private String dataAnnotation;

	private String dataWith;

	private String dataHref;

	private String scriptSrc;

	private String locale;

	public String getCssClass() {
		return cssClass;
	}

	public String getDataAnnotation() {
		return dataAnnotation;
	}

	public String getDataWith() {
		return dataWith;
	}

	public String getDataHref() {
		return dataHref;
	}

	public String getScriptSrc() {
		return scriptSrc;
	}

	public String getLocale() {
		return locale;
	}
	
	public IModel<GooglePlusShareModel> toModel() {
		return Model.of(this);
	}

	public static class Builder {
		private String cssClass;
		private String dataAnnotation;
		private String dataWith;
		private String dataHref;
		private String scriptSrc;
		private String locale;

		public Builder cssClass(String cssClass) {
			this.cssClass = cssClass;
			return this;
		}

		public Builder dataAnnotation(String dataAnnotation) {
			this.dataAnnotation = dataAnnotation;
			return this;
		}

		public Builder dataWith(String dataWith) {
			this.dataWith = dataWith;
			return this;
		}

		public Builder dataHref(String dataHref) {
			this.dataHref = dataHref;
			return this;
		}

		public Builder scriptSrc(String scriptSrc) {
			this.scriptSrc = scriptSrc;
			return this;
		}

		public Builder locale(String locale) {
			this.locale = locale;
			return this;
		}

		public GooglePlusShareModel build() {
			return new GooglePlusShareModel(this);
		}
	}

	private GooglePlusShareModel(Builder builder) {
		this.cssClass = builder.cssClass;
		this.dataAnnotation = builder.dataAnnotation;
		this.dataWith = builder.dataWith;
		this.dataHref = builder.dataHref;
		this.scriptSrc = builder.scriptSrc;
		this.locale = builder.locale;
	}
}