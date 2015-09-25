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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.base.BasePanel;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import lombok.Getter;

/**
 * The Class {@link LinkPanel}.
 */
public abstract class LinkPanel extends BasePanel<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The link component. */
	@Getter
	private final AbstractLink link;

	/** The Label component. */
	@Getter
	private final Component label;

	/** The form. */
	@Getter
	private final Form<?> form;

	/**
	 * Instantiates a new {@link LinkPanel}.
	 *
	 * @param id
	 *            the id
	 * @param labelModel
	 *            the label model
	 */
	public LinkPanel(final String id, final IModel<String> labelModel)
	{
		this(id, labelModel, null);
	}

	/**
	 * Instantiates a new {@link LinkPanel}.
	 *
	 * @param id
	 *            the id
	 * @param labelModel
	 *            the label model
	 * @param form
	 *            the form
	 */
	public LinkPanel(final String id, final IModel<String> labelModel, final Form<?> form)
	{
		super(id, labelModel);
		this.form = form;
		this.setOutputMarkupId(true);
		add(link = newLink("link"));
		link.add(label = newLabel("label", labelModel));
	}

	/**
	 * Factory method for create a new {@link Label}. This method is invoked in the constructor from
	 * the derived classes and can be overridden so users can provide their own version of a new
	 * {@link Label}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link Label}
	 */
	protected Label newLabel(final String id, final IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}

	/**
	 * Factory method for create a new {@link AbstractLink}. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new {@link AbstractLink}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link AbstractLink}
	 */
	protected AbstractLink newLink(final String id)
	{
		return new AjaxLink<Void>(id)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				LinkPanel.this.onClick(target);

			}
		};
	}

	/**
	 * Abstract callback method that have to be overwritten to provide specific action on click.
	 *
	 * @param target
	 *            the target
	 */
	public abstract void onClick(final AjaxRequestTarget target);

}
