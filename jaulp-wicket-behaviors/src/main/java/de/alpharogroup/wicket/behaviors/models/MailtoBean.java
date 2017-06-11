/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
