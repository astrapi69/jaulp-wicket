package org.jaulp.wicket.behaviors.popupoverlay;

import lombok.Getter;

public enum HorizontalPosition {
	CENTER("center"), LEFT("left"), RIGHT("right"), LEFTEDGE("leftedge"), RIGHTEDGE(
			"rightedge");
	@Getter
	private String position;

	private HorizontalPosition(String position) {
		this.position = position;
	}
}
