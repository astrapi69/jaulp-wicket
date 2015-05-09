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
package de.alpharogroup.wicket.components.link;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;

import de.alpharogroup.locale.ResourceBundleKey;

/**
 * The LinkItem is a bean that holds the data for building a link component.
 **/
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkItem implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The page class. */
	private Class<? extends Page> pageClass;

	/** The link class. */
	private Class<? extends Component> linkClass;

	/** The resource model key. */
	@NonNull
	private ResourceBundleKey resourceModelKey;

	/** The url. */
	private String url;

	/** The target. */
	private String target;

	/** The parameters. */
	@Singular
	private Map<String, String> parameters;

	/** The children. */
	@Singular(value = "child")
	private List<LinkItem> children;

	/** The behaviors for the link item. */
	@Singular
	private List<Behavior> behaviors;

}
