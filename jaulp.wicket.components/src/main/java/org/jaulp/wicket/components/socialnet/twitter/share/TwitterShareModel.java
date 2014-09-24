package org.jaulp.wicket.components.socialnet.twitter.share;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class TwitterShareModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String shareUrl;

	private String dataUrl;

	private String via;

	private String counturl;

	private Boolean showCount = Boolean.FALSE;

	private String countAlign;
	
	public String getShareUrl() {
		return shareUrl;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public String getVia() {
		return via;
	}

	public String getCounturl() {
		return counturl;
	}

	public Boolean getShowCount() {
		return showCount;
	}

	public String getCountAlign() {
		return countAlign;
	}

	public IModel<TwitterShareModel> toModel() {
		return Model.of(this);
	}

	public static class Builder {
		private String shareUrl;
		private String dataUrl;
		private String via;
		private String counturl;
		private Boolean showCount = Boolean.FALSE;
		private String countAlign;

		public Builder shareUrl(String shareUrl) {
			this.shareUrl = shareUrl;
			return this;
		}

		public Builder dataUrl(String dataUrl) {
			this.dataUrl = dataUrl;
			return this;
		}

		public Builder via(String via) {
			this.via = via;
			return this;
		}

		public Builder counturl(String counturl) {
			this.counturl = counturl;
			return this;
		}

		public Builder showCount(Boolean showCount) {
			this.showCount = showCount;
			return this;
		}

		public Builder countAlign(String countAlign) {
			this.countAlign = countAlign;
			return this;
		}

		public TwitterShareModel build() {
			return new TwitterShareModel(this);
		}
	}

	private TwitterShareModel(Builder builder) {
		this.shareUrl = builder.shareUrl;
		this.dataUrl = builder.dataUrl;
		this.via = builder.via;
		this.counturl = builder.counturl;
		this.showCount = builder.showCount;
		this.countAlign = builder.countAlign;
	}
}
