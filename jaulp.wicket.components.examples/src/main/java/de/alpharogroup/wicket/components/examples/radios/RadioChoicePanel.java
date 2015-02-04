package de.alpharogroup.wicket.components.examples.radios;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;

public class RadioChoicePanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private static final List<Brands> TYPES = Arrays.asList(Brands.values());

	private Brands selected
	// = Brands.LAMBORGINI
	;

	public RadioChoicePanel(String id, IModel<?> model)
	{
		super(id, model);
		IChoiceRenderer<Brands> renderer = new IChoiceRenderer<Brands>()
		{
			private static final long serialVersionUID = 1L;

			public Object getDisplayValue(Brands object)
			{
				return object.getValue();
			}

			public String getIdValue(Brands object, int index)
			{
				return object.getValue();
			}

			@SuppressWarnings("unused")
			public Brands getObject(String id, IModel<? extends List<? extends Brands>> choices)
			{
				for (Brands brand : choices.getObject())
				{
					if (brand.getValue().equals(id))
					{
						return brand;
					}
				}
				return null;
			}

		};
		RadioChoice<Brands> brandingType = new RadioChoice<Brands>("branding",
			new PropertyModel<Brands>(this, "selected"), TYPES, renderer);
		// dont add a <br> after every radio...
		brandingType.setSuffix("");
		brandingType.setOutputMarkupId(true);
		brandingType.add(new AjaxFormChoiceComponentUpdatingBehavior()
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target)
			{
				target.add(getFeedback());
				info("Selected Type : " + selected.getValue());
			}
		});
		Form<?> form = new Form<Void>("form");

		add(form);
		form.add(brandingType);
	}

	protected Component getFeedback()
	{
		PubliclyBasePage<?> basePage = (PubliclyBasePage<?>)getPage();
		return basePage.getFeedback();
	}
}
