package de.alpharogroup.wicket.components.sign.in.password.reset;

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
public class ResetPasswordModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String username;
	private String confirmationCode;
}
