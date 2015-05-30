package de.alpharogroup.wicket.components.examples.labeled.address;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.examples.fragment.swapping.HomeAddress;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentPanel;
import de.alpharogroup.wicket.components.labeled.LabeledTwoFormComponentPanel;

public class AddressPanel extends GenericPanel<HomeAddress>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public AddressPanel(String id, final IModel<HomeAddress> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		IModel<TwoFormComponentBean<String, String>> streetNumberCompModel = Model
			.of(new TwoFormComponentBean<String, String>());
		streetNumberCompModel.getObject().setLeftContent(getModelObject().getStreet());
		streetNumberCompModel.getObject().setRightContent(getModelObject().getLocalNumber());

		LabeledTwoFormComponentPanel<String, String> streetNumberPanel = new LabeledTwoFormComponentPanel<String, String>(
			"streetNumberPanel", streetNumberCompModel, Model.of("Street / number:")) {
			/**
				 * The serialVersionUID
				 */
				private static final long serialVersionUID = 1L;

			@Override
			protected TwoFormComponentPanel<String, String> newTwoFormComponentPanel(String id,
				IModel<TwoFormComponentBean<String, String>> model01)
			{
				TwoFormComponentPanel<String, String> panel = new TwoFormComponentPanel<String, String>(id, model01) {
					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;
					@Override
					protected FormComponent<String> newLeftFormComponent(String id,
						IModel<String> model02)
					{
						return ComponentFactory.newTextField(id, new PropertyModel<String>(AddressPanel.this.getModelObject(), "street"));
					}
					@Override
					protected FormComponent<String> newRightFormComponent(String id,
						IModel<String> model)
					{
						return ComponentFactory.newTextField(id, new PropertyModel<String>(AddressPanel.this.getModelObject(), "localNumber"));
					}
				};
				return panel;
			}
		};

		add(streetNumberPanel);

		IModel<TwoFormComponentBean<String, String>> zipcodeCityCompModel = Model
			.of(new TwoFormComponentBean<String, String>());
		zipcodeCityCompModel.getObject().setLeftContent(getModelObject().getCode());
		zipcodeCityCompModel.getObject().setRightContent(getModelObject().getCity());

		LabeledTwoFormComponentPanel<String, String> zipcodeCityPanel = new LabeledTwoFormComponentPanel<String, String>(
			"zipcodeCityPanel", zipcodeCityCompModel, Model.of("Zip / City:")) {
			/**
				 * The serialVersionUID
				 */
				private static final long serialVersionUID = 1L;

			@Override
			protected TwoFormComponentPanel<String, String> newTwoFormComponentPanel(String id,
				IModel<TwoFormComponentBean<String, String>> model01)
			{
				TwoFormComponentPanel<String, String> panel = new TwoFormComponentPanel<String, String>(id, model01) {
					/**
					 * The serialVersionUID
					 */
					private static final long serialVersionUID = 1L;
					@Override
					protected FormComponent<String> newLeftFormComponent(String id,
						IModel<String> model02)
					{
						return ComponentFactory.newTextField(id, new PropertyModel<String>(AddressPanel.this.getModelObject(), "code"));
					}
					@Override
					protected FormComponent<String> newRightFormComponent(String id,
						IModel<String> model)
					{
						return ComponentFactory.newTextField(id, new PropertyModel<String>(AddressPanel.this.getModelObject(), "city"));
					}
				};
				return panel;
			}
		};

		add(zipcodeCityPanel);
	}

}
