package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import java.io.Serializable;

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
public class PersonModel implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String firstName;

	private String lastName;

	private String gender;

	private String age;

}
