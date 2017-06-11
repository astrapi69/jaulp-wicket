package de.alpharogroup.wicket.behaviors.models;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link MailtoBean} holds the values for build the content of a
 * mailto in a href attribute from an 'a' link tag.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MailtoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/** The email. */
	@NonNull
	private String email;

	/** The subject. */
	private String subject;

	/** The body. */
	private String body;

	/**
	 * Builds the mailto content.
	 *
	 * @return the result of this bean as a {@link String} object.
	 */
	public String buildMailtoContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(email);
		if (StringUtils.isNotEmpty(subject)) {
			sb.append("?").append("subject").append("=").append(subject);
		}
		if (StringUtils.isNotEmpty(body)) {
			sb.append(StringUtils.isNotEmpty(subject) ? "&" : "?").append("body").append("=").append(body);
		}
		return sb.toString().trim();
	}
}
