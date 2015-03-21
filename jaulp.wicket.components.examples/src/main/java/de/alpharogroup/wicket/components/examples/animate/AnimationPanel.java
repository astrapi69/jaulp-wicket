package de.alpharogroup.wicket.components.examples.animate;

import lombok.Getter;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;
import org.jaulp.wicket.behaviors.BuildableChainableStatement;
import org.jaulp.wicket.behaviors.JqueryStatementsBehavior;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public class AnimationPanel extends Panel
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/** The yes button. */
	@Getter
	private final AjaxButton animateButton;

	public AnimationPanel(String id, IModel<?> model)
	{
		super(id, model);

		add(animateButton = newAnimateButton("animateButton"));
		WebMarkupContainer containerAnimate = new WebMarkupContainer("containerAnimate");
		add(containerAnimate);

		JqueryStatementsBehavior jqueryStatementsBehavior = new JqueryStatementsBehavior()
		.add(new BuildableChainableStatement.Builder().label("animate")
			.args("{height: '300'}").build());
		containerAnimate.add(jqueryStatementsBehavior);
	}
	


	protected AjaxButton newAnimateButton(final String id)
	{
		final AjaxButton ajaxButton = new AjaxButton(id)
		{
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				onAnimate(target, form, true);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				onAnimate(target, form, false);
			}

		};
		final IModel<String> yesLabelModel = new StringResourceModel("animate.label", this, null);
		ajaxButton.add(newLabel("animateLabel", yesLabelModel));
		return ajaxButton;
	}

	/**
	 * Factory method for creating the Label. This method is invoked in the constructor from the
	 * derived classes and can be overridden so users can provide their own version of a Label.
	 * 
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the label
	 */
	protected Label newLabel(String id, IModel<String> model)
	{
		return ComponentFactory.newLabel(id, model);
	}
	
	protected void onAnimate(final AjaxRequestTarget target, Form<?> form, boolean error) {
		
	}

}
