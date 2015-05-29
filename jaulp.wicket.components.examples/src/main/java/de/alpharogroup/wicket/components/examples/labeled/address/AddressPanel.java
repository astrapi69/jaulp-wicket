package de.alpharogroup.wicket.components.examples.labeled.address;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.alpharogroup.wicket.components.examples.fragment.swapping.HomeAddress;
import de.alpharogroup.wicket.components.form.input.TwoFormComponentBean;
import de.alpharogroup.wicket.components.labeled.LabeledTwoFormComponentPanel;

public class AddressPanel extends GenericPanel<HomeAddress>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public AddressPanel(String id, IModel<HomeAddress> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		IModel<TwoFormComponentBean<String, String>> streetNumberCompModel = Model
			.of(new TwoFormComponentBean<String, String>());
		streetNumberCompModel.getObject().setLeftContent(model.getObject().getStreet());
		streetNumberCompModel.getObject().setRightContent(model.getObject().getLocalNumber());
		
		LabeledTwoFormComponentPanel<String, String> streetNumberPanel = 
			new LabeledTwoFormComponentPanel<String, String>("streetNumberPanel",
				streetNumberCompModel, Model.of("Street / number:"));
		
		add(streetNumberPanel);		
		
		IModel<TwoFormComponentBean<String, String>> zipcodeCityCompModel = Model
			.of(new TwoFormComponentBean<String, String>());
		zipcodeCityCompModel.getObject().setLeftContent(model.getObject().getCode());
		zipcodeCityCompModel.getObject().setRightContent(model.getObject().getCity());
		
		LabeledTwoFormComponentPanel<String,String> zipcodeCityPanel = 
			new LabeledTwoFormComponentPanel<String,String>("zipcodeCityPanel",
				zipcodeCityCompModel, Model.of("Zip / City:"));
		
		add(zipcodeCityPanel);
	}

}
