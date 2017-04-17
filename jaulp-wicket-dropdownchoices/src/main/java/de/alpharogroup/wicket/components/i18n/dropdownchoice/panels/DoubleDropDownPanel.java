package de.alpharogroup.wicket.components.i18n.dropdownchoice.panels;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.i18n.dropdownchoice.LocalisedDropDownChoice;
import lombok.Getter;

public class DoubleDropDownPanel<M> extends FormComponentPanel<M>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ROOT_CHOICE_ID. */
	public static final String ROOT_CHOICE_ID = "rootChoice";

	/** The Constant CHILD_CHOICE_ID. */
	public static final String CHILD_CHOICE_ID = "childChoice";

	/** The root choice. */
	@Getter
	private DropDownChoice<T> rootChoice;

	/** The child choice. */
	@Getter
	private DropDownChoice<T> childChoice;

	/** The Label component. */
	protected Label label;


	public DoubleDropDownPanel(String id, IModel<M> model,
		final IChoiceRenderer<M> rootRenderer, final IChoiceRenderer<M> childRenderer)
	{
		super(id, model);
	}

	/**
	 * Factory method for creating the new root {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new root {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 * @return the new root {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newRootChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices,
		final IChoiceRenderer<? super T> renderer)
	{
		final DropDownChoice<T> rc = new LocalisedDropDownChoice<>(id, model, choices, renderer);
		rc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				target.add(childChoice);
			}
		});
		return rc;
	}

	/**
	 * Factory method for creating the new child {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new child {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            the choices
	 * @param renderer
	 *            the renderer
	 * @return the new child {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newChildChoice(final String id, final IModel<T> model,
		final IModel<? extends List<? extends T>> choices,
		final IChoiceRenderer<? super T> renderer)
	{
		final DropDownChoice<T> cc = new LocalisedDropDownChoice<>(id, model, choices, renderer);
		cc.setOutputMarkupId(true);
		return cc;
	}

}
