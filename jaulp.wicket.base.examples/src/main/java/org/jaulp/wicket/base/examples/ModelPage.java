package org.jaulp.wicket.base.examples;


import static org.wicketeer.modelfactory.ModelFactory.from;
import static org.wicketeer.modelfactory.ModelFactory.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.test.objects.Person;
import org.jaulp.wicket.base.GenericBasePage;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/models")
public class ModelPage extends GenericBasePage<Person>
{
	IModel<String> nicknameModel;

	public ModelPage(final PageParameters parameters)
	{
		super(parameters);
		setModel(Model.of(new Person()));
		// set nickname null...
		getModelObject().setNickname(null);
		// Set PropertyModel to an Object that is null...
		nicknameModel = model(from(getModel()).getNickname());
		add(new MenubarPanel("menubarPanel"));
		String nickname = nicknameModel.getObject();
		add(new Label("text", nicknameModel).setOutputMarkupId(true));
		// TODO add an AjaxLink to change the nickname...

		AjaxLink<Void> updateLabelLink = new AjaxLink<Void>("updateLabelLink")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				// set nickname to foo...
				ModelPage.this.getModelObject().setNickname("foo");
				target.add(ModelPage.this.get("text"));
			}
		};
		add(updateLabelLink);

	}

}
