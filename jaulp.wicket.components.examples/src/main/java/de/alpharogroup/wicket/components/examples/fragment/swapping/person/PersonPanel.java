package de.alpharogroup.wicket.components.examples.fragment.swapping.person;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.swap.SwapComponentsFragmentPanel;

public class PersonPanel extends SwapComponentsFragmentPanel<PersonModel>
{

	private static final long serialVersionUID = 1L;

	public PersonPanel(String id, IModel<PersonModel> model)
	{
		super(id, model);
	}

	protected Component newViewComponent(String id, IModel<PersonModel> model)
	{
		return new ViewPersonPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			protected void onSubmit(AjaxRequestTarget target)
			{
				swapFragments(target, null);
			}
		};
	}

	protected Component newEditComponent(String id, IModel<PersonModel> model)
	{
		return new EditPersonPanel(id, model)
		{
			private static final long serialVersionUID = 1L;

			protected void onSubmit(AjaxRequestTarget target, final Form<?> form)
			{
				swapFragments(target, form);
			}
		};
	}

}