package de.alpharogroup.wicket.components.radio;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;
@Getter 
@Setter 
@EqualsAndHashCode 
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadioGroupModel<T> implements Serializable {
	
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	T selected;
	List<T> radios;

}
