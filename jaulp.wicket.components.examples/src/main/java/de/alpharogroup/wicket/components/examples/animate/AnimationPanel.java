/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.examples.animate;

import de.alpharogroup.wicket.behaviors.JavascriptAppenderBehavior;
import de.alpharogroup.wicket.behaviors.spin.SpinJsGenerator;
import de.alpharogroup.wicket.behaviors.spin.SpinResourceReference;
import de.alpharogroup.wicket.behaviors.spin.SpinSettings;
import lombok.Getter;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.effects.EffectBehavior;
import org.odlabs.wiquery.core.effects.EffectSpeed;
import org.odlabs.wiquery.core.effects.fading.FadeTo;

import de.alpharogroup.locale.ResourceBundleKey;
import de.alpharogroup.wicket.base.util.resource.ResourceModelFactory;
import de.alpharogroup.wicket.behaviors.BuildableChainableStatement;
import de.alpharogroup.wicket.behaviors.JqueryStatementsBehavior;
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

		Form<?> form = ComponentFactory.newForm("form");
		add(form);
		form.add(animateButton = newAnimateButton("animateButton", form));

		WebMarkupContainer containerAnimate = new WebMarkupContainer("containerAnimate");
		form.add(containerAnimate);

		JqueryStatementsBehavior jqueryStatementsBehavior = new JqueryStatementsBehavior().add(
			new BuildableChainableStatement.Builder().label("animate").args("{height: '300'}")
				.build()).add(
			new BuildableChainableStatement.Builder().label("animate").args("{left: '300px'}")
				.build());
		String render = (String)jqueryStatementsBehavior.createRenderedStatement(containerAnimate);
		System.out.println(render);
		containerAnimate.add(jqueryStatementsBehavior);
		containerAnimate.add(new EffectBehavior(new FadeTo(EffectSpeed.SLOW, 0.4f)));
		containerAnimate.add(new EffectBehavior(new FadeTo(EffectSpeed.SLOW, 1.0f)));

    // add a spinner...
    SpinSettings spinSettings = new SpinSettings();
    spinSettings.getColor().setValue("#00ff00");
    spinSettings.getDirection().setValue(-1);
    spinSettings.getSpeed().setValue(1.2f);
    spinSettings.getPosition().setValue("relative");
    String js = new SpinJsGenerator(spinSettings, animateButton.getMarkupId()).generateJs();
    add(new JavascriptAppenderBehavior(js));
	}


	protected AjaxButton newAnimateButton(final String id, Form<?> form)
	{
		final AjaxButton ajaxButton = new AjaxButton(id, form)
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
		final IModel<String> yesLabelModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key("animate.label").defaultValue("").build(), this);
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

	protected void onAnimate(final AjaxRequestTarget target, Form<?> form, boolean error)
	{

	}

  @Override
  public void renderHead(final IHeaderResponse response) {
    super.renderHead(response);
    response.render(JavaScriptHeaderItem.forReference(Application.get()
      .getJavaScriptLibrarySettings().getJQueryReference()));
    response.render(JavaScriptHeaderItem.forReference(SpinResourceReference.get()));
  }
}
