package de.alpharogroup.wicket.components.examples.labeled.address;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.examples.fragment.swapping.HomeAddress;
import de.alpharogroup.wicket.components.factory.ComponentFactory;
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

		LabeledTwoFormComponentPanel<String, String> streetNumberPanel = new LabeledTwoFormComponentPanel<String, String>(
			"streetNumberPanel", Model.of("Street / number:"))
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected FormComponent<String> newLeftFormComponent(String id, IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "street"));
			}

			@Override
			protected FormComponent<String> newRightFormComponent(String id, IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "localNumber"));
			}
		};

		add(streetNumberPanel);

		LabeledTwoFormComponentPanel<String, String> zipcodeCityPanel = new LabeledTwoFormComponentPanel<String, String>(
			"zipcodeCityPanel", Model.of("Zip / City:"))
		{
			/**
			 * The serialVersionUID
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected FormComponent<String> newLeftFormComponent(String id, IModel<String> model02)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "code"));
			}

			@Override
			protected FormComponent<String> newRightFormComponent(String id, IModel<String> model)
			{
				return ComponentFactory.newTextField(id, new PropertyModel<String>(
					AddressPanel.this.getModelObject(), "city"));
			}
		};

		add(zipcodeCityPanel);
	}

}