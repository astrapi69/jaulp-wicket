package de.alpharogroup.wicket.components.labeled;

import java.io.Serializable;

import lombok.Getter;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentPanel;

/**
 * The Class LabeledTwoFormComponentPanel is a container for two FormComponent. Default they are
 * TextField objects but can be overwritten by the factory methods to return any other input field.
 *
 * @param <L>
 *            the generic type of the model from the left FormComponent
 * @param <R>
 *            the generic type of the model from the left FormComponent
 */
public class LabeledTwoFormComponentPanel<L extends Serializable, R extends Serializable>
	extends
		LabeledFormComponentPanel<TwoFormComponentBean<L, R>>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The left text field.
	 */
	@Getter
	private TwoFormComponentPanel<L, R> twoFormComponent;


	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public LabeledTwoFormComponentPanel(String id, IModel<String> labelModel)
	{
		this(id, Model.of(new TwoFormComponentBean<L, R>()), labelModel);
	}

	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public LabeledTwoFormComponentPanel(String id, IModel<TwoFormComponentBean<L, R>> model,
		IModel<String> labelModel)
	{
		super(id, model, labelModel);
		add(twoFormComponent = newTwoFormComponentPanel("twoFormComponent", model));

		add(feedback = newComponentFeedbackPanel("feedback", twoFormComponent));

		String markupId = twoFormComponent.getMarkupId();
		add(label = newLabel("label", markupId, getLabel()));
	}

	/**
	 * New left text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected TwoFormComponentPanel<L, R> newTwoFormComponentPanel(String id,
		IModel<TwoFormComponentBean<L, R>> model)
	{
		TwoFormComponentPanel<L, R> twoFormComponentPanel = new TwoFormComponentPanel<L, R>(id,
			model)
		{

			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			protected FormComponent<L> newLeftFormComponent(String id, IModel<L> model)
			{
				return LabeledTwoFormComponentPanel.this.newLeftFormComponent(id, model);
			}

			protected FormComponent<R> newRightFormComponent(String id, IModel<R> model)
			{
				return LabeledTwoFormComponentPanel.this.newRightFormComponent(id, model);
			}

		};
		return twoFormComponentPanel;
	}

	/**
	 * New left text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected FormComponent<L> newLeftFormComponent(String id, IModel<L> model)
	{
		return ComponentFactory.newTextField(id, model);
	}

	/**
	 * New right text field.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form component
	 */
	protected FormComponent<R> newRightFormComponent(String id, IModel<R> model)
	{
		return ComponentFactory.newTextField(id, model);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void convertInput()
	{
		setConvertedInput(getModelObject());
	}

}
