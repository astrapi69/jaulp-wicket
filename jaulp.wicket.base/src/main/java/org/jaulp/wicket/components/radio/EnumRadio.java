package org.jaulp.wicket.components.radio;

import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;

/**
 * The Class EnumRadio extends the class Radio and takes as parameter an enum type.
 * 
 * @param <T>
 *            the generic type that must extends enum.
 * @author Asterios Raptis
 */
public class EnumRadio<T extends Enum<?>> extends Radio<T> {
	
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -8391468655460799084L;

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param group
	 *            the group
	 */
	public EnumRadio(String id, IModel<T> model, RadioGroup<T> group) {
		super(id, model, group);
	}

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 * @param group
	 *            the group
	 */
	public EnumRadio(String id, RadioGroup<T> group) {
		super(id, group);
	}

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 */
	public EnumRadio(String id) {
		super(id);
	}

	/**
	 * Instantiates a new enum radio.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 */
	public EnumRadio(String id, IModel<T> model) {
		super(id, model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue() {
		return getModelObject().name();
	}

}
