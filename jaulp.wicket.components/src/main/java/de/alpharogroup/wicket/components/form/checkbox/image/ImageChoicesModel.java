package de.alpharogroup.wicket.components.form.checkbox.image;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ImageChoicesModel implements Serializable {
	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private List<ImageCheckboxModel> choices;
	List<ImageCheckboxModel> selectedItems = new ArrayList<ImageCheckboxModel>();
	private String labelPropertyExpression;
}
