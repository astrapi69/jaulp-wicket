package de.alpharogroup.wicket.components.form.input;

import lombok.Getter;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

/**
 * The Class TwoFormComponentPanel is a container for two FormComponent. Default they are TextField
 * objects but can be overwritten by the factory methods to return any other input field.
 *
 * @param <L>
 *            the generic type of the model from the left FormComponent
 * @param <R>
 *            the generic type of the model from the left FormComponent
 */
public class TwoFormComponentPanel<L, R> extends FormComponentPanel<TwoFormComponentBean<L, R>>
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The left text field.
	 */
	@Getter
	private FormComponent<L> leftFormComponent;

	/**
	 * The right text field.
	 */
	@Getter
	private FormComponent<R> rightFormComponent;

	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 */
	public TwoFormComponentPanel(String id)
	{
		this(id, null);
	}

	/**
	 * Instantiates a new two text field panel.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public TwoFormComponentPanel(String id, IModel<TwoFormComponentBean<L, R>> model)
	{
		super(id, model);
		setType(TwoFormComponentBean.class);
		add(leftFormComponent = newLeftFormComponent("leftTextField", model)).add(
			rightFormComponent = newRightFormComponent("rightTextField", model));
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
	protected FormComponent<L> newLeftFormComponent(String id,
		IModel<TwoFormComponentBean<L, R>> model)
	{
		return ComponentFactory.newTextField(id, new PropertyModel<L>(model, "leftContent"));
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
	protected FormComponent<R> newRightFormComponent(String id,
		IModel<TwoFormComponentBean<L, R>> model)
	{
		return ComponentFactory.newTextField(id, new PropertyModel<R>(model, "rightContent"));
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
