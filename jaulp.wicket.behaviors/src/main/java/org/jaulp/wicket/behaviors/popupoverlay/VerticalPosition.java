package org.jaulp.wicket.behaviors.popupoverlay;

import lombok.Getter;

public enum VerticalPosition
{
	CENTER("center"), TOP("top"), BOTTOM("bottom"), TOPEDGE("topedge"), BOTTOMEDGE("bottomedge");
	@Getter
	private String position;

	private VerticalPosition(String position)
	{
		this.position = position;
	}
}
