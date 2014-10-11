package de.alpharogroup.wicket.components.examples.ajaxtabs.addtab;

import java.io.Serializable;

public class TabbedPanelModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tabOne = "";
	private String tabTwo = "";
	private String tabThree = "";
	public String getTabOne() {
		return tabOne;
	}
	public void setTabOne(String tabOne) {
		this.tabOne = tabOne;
	}
	public String getTabTwo() {
		return tabTwo;
	}
	public void setTabTwo(String tabTwo) {
		this.tabTwo = tabTwo;
	}
	public String getTabThree() {
		return tabThree;
	}
	public void setTabThree(String tabThree) {
		this.tabThree = tabThree;
	}

}
