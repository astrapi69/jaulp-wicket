package de.alpharogroup.wicket.components.labeled.checkbox.img;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

import org.apache.wicket.request.resource.IResource;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabeledImageCheckboxModel implements Serializable
{
	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private IResource imageResource;
	private Boolean checked = Boolean.FALSE;
}
