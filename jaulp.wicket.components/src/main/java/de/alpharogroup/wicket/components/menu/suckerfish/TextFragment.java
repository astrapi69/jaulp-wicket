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
package de.alpharogroup.wicket.components.menu.suckerfish;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;

/**
 * The Class TextFragment.
 * 
 * @author Asterios Raptis
 */
class TextFragment extends Fragment
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 0L;

	/**
	 * Instantiates a new text fragment.
	 * 
	 * @param label
	 *            the label
	 * @param markupProvider
	 *            the {@link MarkupContainer}
	 */
	public TextFragment(final Label label, final MarkupContainer markupProvider)
	{
		super("linkfragment", "TEXTFRAGMENT", markupProvider);
		add(label);
	}
}