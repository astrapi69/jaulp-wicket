package de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter 
@EqualsAndHashCode 
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TabbedPanelModels<T> implements Serializable {

	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(TabbedPanelModels.class.getName());

	private static final long serialVersionUID = 1L;
	List<TabModel<T>> tabModels;
	
	public TabbedPanelModels<T> add(TabModel<T> tabModel) {
		if(getTabModels()== null) {
			LOGGER.info("The list tabModels is null! Please set the list tabModels and add than the Given tabModel.");
			return this;
		}
		getTabModels().add(tabModel);
		return this;
	}
	
	public TabbedPanelModels<T> remove(TabModel<T> tabModel) {
		if(getTabModels()== null) {
			LOGGER.info("The list tabModels is null! Given tabModel can not be removed.");
			return this;
		}
		getTabModels().remove(tabModel);
		return this;
	}
}