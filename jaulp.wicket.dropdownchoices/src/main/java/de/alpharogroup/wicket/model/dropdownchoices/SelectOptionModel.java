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
package de.alpharogroup.wicket.model.dropdownchoices;

import lombok.Getter;
import lombok.Setter;

import org.apache.wicket.util.io.IClusterable;

/**
 * The Class {@link SelectOptionModel}.
 */
public class SelectOptionModel implements IClusterable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7091799192618838124L;

	/** The key. */
	@Getter
	@Setter
	private String key;

	/** The value. */
	@Getter
	@Setter
	private String value;

	/**
	 * Instantiates a new {@link SelectOptionModel}.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public SelectOptionModel(final String key, final String value)
	{
		this.key = key;
		this.value = value;
	}

}
