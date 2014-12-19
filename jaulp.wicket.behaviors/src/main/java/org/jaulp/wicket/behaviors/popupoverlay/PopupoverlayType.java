package org.jaulp.wicket.behaviors.popupoverlay;

import lombok.Getter;

public enum PopupoverlayType {
	OVERLAY("overlay"), TOOLTIP("tooltip");
	@Getter
	private String type;

	private PopupoverlayType(String type) {
		this.type = type;
	}
}
