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
package de.alpharogroup.wicket.components.factory;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import de.alpharogroup.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.EnumLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.IResource;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;

/**
 * A factory for create Component objects. All components are setting
 * Component#setOutputMarkupId(boolean) to true, so they are ajaxifiable.
 */
public class ComponentFactory
{

	/**
	 * Factory method for create a new CheckBox.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the created CheckBox
	 */
	public static CheckBox newCheckBox(final String id, final IModel<Boolean> model)
	{
		CheckBox checkBox = new CheckBox(id, model);
		checkBox.setOutputMarkupId(true);
		return checkBox;
	}

	/**
	 * Factory method for create a new ComponentFeedbackPanel.
	 *
	 * @param id
	 *            the id
	 * @param filter
	 *            the filter
	 * @return the component feedback panel
	 */
	public static ComponentFeedbackPanel newComponentFeedbackPanel(final String id,
		final Component filter)
	{
		ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id, filter);
		feedbackPanel.setOutputMarkupId(true);
		return feedbackPanel;
	}


	/**
	 * Factory method for creating a new DateTextField.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the DateTextField.
	 */
	public static DateTimeField newDateTimeField(String id, IModel<Date> model)
	{
		DateTimeField dateTextField = new DateTimeField(id, model);
		dateTextField.setOutputMarkupId(true);
		return dateTextField;
	}

	/**
	 * Factory method for create a new {@link DropDownChoice}.
	 *
	 * @param <T>
	 *            the generic type of the {@link DropDownChoice}
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            The collection of choices in the dropdown
	 * @return the new {@link DropDownChoice}
	 */
	public static <T> DropDownChoice<T> newDropDownChoice(final String id, IModel<T> model,
		final List<? extends T> choices)
	{
		DropDownChoice<T> dropDownChoice = new DropDownChoice<T>(id, model, choices);
		dropDownChoice.setOutputMarkupId(true);
		return dropDownChoice;
	}

	/**
	 * Factory method for create a new {@link DropDownChoice}.
	 *
	 * @param <T>
	 *            the generic type of the {@link DropDownChoice}
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            The collection of choices in the dropdown
	 * @param renderer
	 *            The rendering engine
	 * @return the new {@link DropDownChoice}
	 */
	public static <T> DropDownChoice<T> newDropDownChoice(final String id, IModel<T> model,
		final List<? extends T> choices, final IChoiceRenderer<? super T> renderer)
	{
		DropDownChoice<T> dropDownChoice = new DropDownChoice<T>(id, model, choices, renderer);
		dropDownChoice.setOutputMarkupId(true);
		return dropDownChoice;
	}

	/**
	 * Factory method for create a new EnumLabel.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model of the label
	 * @return the enum label
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> EnumLabel newEnumLabel(final String id, final IModel<T> model)
	{
		EnumLabel enumLabel = new EnumLabel(id, model)
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected String resourceKey(Enum value)
			{
				return value.name();
			}
		};
		enumLabel.setOutputMarkupId(true);
		return enumLabel;
	}

	/**
	 * Factory method for creating a new {@link EmailTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link EmailTextField}
	 */
	public static EmailTextField newEmailTextField(String id, IModel<String> model)
	{
		EmailTextField emailTextField = new EmailTextField(id, model);
		emailTextField.setOutputMarkupId(true);
		return emailTextField;
	}

	/**
	 * Factory method for creating a new {@link PasswordTextField}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link PasswordTextField}
	 */
	public static PasswordTextField newPasswordTextField(String id, IModel<String> model)
	{
		PasswordTextField passwordTextField = new PasswordTextField(id, model);
		passwordTextField.setOutputMarkupId(true);
		return passwordTextField;
	}

	/**
	 * Factory method for create a new FeedbackPanel.
	 *
	 * @param id
	 *            the id
	 * @return the FeedbackPanel
	 */
	public static FeedbackPanel newFeedbackPanel(final String id)
	{
		FeedbackPanel feedbackPanel = new FeedbackPanel(id);
		feedbackPanel.setOutputMarkupId(true);
		return feedbackPanel;
	}

	/**
	 * Factory method for create a new Form.
	 *
	 * @param <T>
	 *            the generic type of the form
	 * @param id
	 *            the id
	 * @return the form
	 */
	public static <T> Form<T> newForm(final String id)
	{
		return newForm(id, null);
	}

	/**
	 * Factory method for create a new Form.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the form
	 */
	public static <T> Form<T> newForm(final String id, final IModel<T> model)
	{
		Form<T> form = new Form<>(id, model);
		form.setOutputMarkupId(true);
		return form;
	}

	/**
	 * Factory method for create a new Fragment.
	 * 
	 * @param id
	 *            the id
	 * @param markupId
	 *            The associated id of the associated markup fragment
	 * @param markupProvider
	 *            The component whose markup contains the fragment's markup
	 * @return The new Fragment.
	 */
	public static Fragment newFragment(final String id, final String markupId,
		final MarkupContainer markupProvider)
	{
		return newFragment(id, markupId, markupProvider, null);
	}

	/**
	 * Factory method for create a new Fragment.
	 * 
	 * @param id
	 *            the id
	 * @param markupId
	 *            The associated id of the associated markup fragment
	 * @param markupProvider
	 *            The component whose markup contains the fragment's markup
	 * @param model
	 *            The model for this fragment
	 * @return The new Fragment.
	 */
	public static <T> Fragment newFragment(final String id, final String markupId,
		final MarkupContainer markupProvider, final IModel<T> model)
	{
		Fragment fragment = new Fragment(id, markupId, markupProvider, model);
		fragment.setOutputMarkupId(true);
		return fragment;
	}

	/**
	 * Factory method for create a new hidden field.
	 *
	 * @param id
	 *            the id
	 * @return the component
	 */
	public static Component newHiddenField(final String id)
	{
		HiddenField<String> hiddenField = new HiddenField<>(id);
		hiddenField.setOutputMarkupId(true);
		return hiddenField;
	}

	/**
	 * Factory method for create a new Image.
	 *
	 * @param id
	 *            the id
	 * @param imageResource
	 *            the IResource object
	 * @return the created Image
	 */
	public static Image newImage(final String id, final IResource imageResource)
	{
		Image image = new Image(id, imageResource);
		image.setOutputMarkupId(true);
		return image;
	}


	/**
	 * Factory method for create a new Label with a {@link IModel}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the {@link IModel} for the label.
	 * @return the label
	 */
	public static <T> Label newLabel(final String id, final IModel<T> model)
	{
		Label label = new Label(id, model);
		label.setOutputMarkupId(true);
		return label;
	}

	/**
	 * Factory method for create a new {@link Label} with a {@link ResourceBundleKey}.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param component
	 *            the component to find resource keys
	 * @return the new {@link Label}
	 */
	public static Label newLabel(final String id, final ResourceBundleKey resourceKey,
		final Component component)
	{
		return ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(resourceKey, component));
	}

	/**
	 * Factory method for create a new Label with the for attribute.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param model
	 *            the model
	 * @return the label
	 */
	public static <T> Label newLabel(final String id, final String forId, final IModel<T> model)
	{
		Label label = new Label(id, model);
		label.add(new AttributeAppender("for", Model.of(forId), " "));
		label.setOutputMarkupId(true);
		return label;
	}


	/**
	 * Factory method for create a new Label with the for attribute.
	 *
	 * @param id
	 *            the id
	 * @param forId
	 *            the for id
	 * @param resourceBundleKey
	 *            the resource key
	 * @param component
	 *            the component to find resource keys
	 * @return the label
	 */
	public static Label newLabel(final String id, final String forId,
		final ResourceBundleKey resourceBundleKey, final Component component)
	{
		return ComponentFactory.newLabel(id, forId,
			ResourceModelFactory.newResourceModel(resourceBundleKey, component));
	}

	/**
	 * Factory method for create a new Label.
	 *
	 * @param id
	 *            the id
	 * @param resourceKey
	 *            the resource key
	 * @param defaultValue
	 *            the default value
	 * @param component
	 *            the component
	 * @return the label
	 */
	public static Label newLabel(final String id, final String resourceKey,
		final String defaultValue, final Component component)
	{
		return ComponentFactory.newLabel(id,
			ResourceModelFactory.newResourceModel(resourceKey, component, defaultValue));
	}

	/**
	 * Factory method for create a new MultiLineLabel with a {@link IModel}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the {@link IModel} for the label.
	 * @return the label
	 */
	public static <T> MultiLineLabel newMultiLineLabel(final String id, final IModel<T> model)
	{
		MultiLineLabel multiLineLabel = new MultiLineLabel(id, model);
		multiLineLabel.setOutputMarkupId(true);
		return multiLineLabel;
	}

	/**
	 * Factory method for create a new TextArea.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the text area
	 */
	public static <T> TextArea<T> newTextArea(final String id, final IModel<T> model)
	{
		TextArea<T> textArea = new TextArea<>(id, model);
		textArea.setOutputMarkupId(true);
		return textArea;
	}

	/**
	 * Factory method for create a new {@link TextField}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @return the new {@link TextField}
	 */
	public static <T> TextField<T> newTextField(final String id)
	{
		return newTextField(id, null);
	}

	/**
	 * Factory method for create a new {@link TextField}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link TextField}
	 */
	public static <T> TextField<T> newTextField(final String id, final IModel<T> model)
	{
		TextField<T> textField = new TextField<>(id, model);
		textField.setOutputMarkupId(true);
		return textField;
	}

	/**
	 * Factory method for create a new {@link RadioGroup}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @return the new {@link RadioGroup}
	 */
	public static <T> RadioGroup<T> newRadioGroup(final String id)
	{
		return newRadioGroup(id, null);
	}

	/**
	 * Factory method for create a new {@link RadioGroup}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link RadioGroup}
	 */
	public static <T> RadioGroup<T> newRadioGroup(final String id, final IModel<T> model)
	{
		RadioGroup<T> radioGroup = new RadioGroup<>(id, model);
		radioGroup.setOutputMarkupId(true);
		return radioGroup;
	}

	/**
	 * Factory method for create a new {@link RadioChoice}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            The list of choices in the radio choice
	 * @param renderer
	 *            the renderer
	 * @return the new {@link RadioChoice}
	 */
	public static <T> RadioChoice<T> newRadioChoice(final String id, IModel<T> model,
		final List<? extends T> choices, final IChoiceRenderer<? super T> renderer)
	{
		RadioChoice<T> radioChoice = new RadioChoice<T>(id, model, choices, renderer);
		radioChoice.setOutputMarkupId(true);
		return radioChoice;
	}

	/**
	 * Factory method for create a new {@link RadioChoice}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @param choices
	 *            The list of choices in the radio choice
	 * @return the new {@link RadioChoice}
	 */
	public static <T> RadioChoice<T> newRadioChoice(final String id, IModel<T> model,
		final List<? extends T> choices)
	{
		RadioChoice<T> radioChoice = new RadioChoice<T>(id, model, choices);
		radioChoice.setOutputMarkupId(true);
		return radioChoice;
	}

	/**
	 * Factory method for create a new {@link RequiredTextField}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link RequiredTextField}
	 */
	public static <T> RequiredTextField<T> newRequiredTextField(final String id,
		final IModel<T> model)
	{
		RequiredTextField<T> requiredTextField = new RequiredTextField<T>(id, model);
		requiredTextField.setOutputMarkupId(true);
		return requiredTextField;
	}

	/**
	 * Factory method for create a new {@link CheckGroup}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link CheckGroup}
	 */
	public static <T> CheckGroup<T> newCheckGroup(final String id,
		final IModel<? extends Collection<T>> model)
	{
		CheckGroup<T> checkGroup = new CheckGroup<>(id, model);
		checkGroup.setOutputMarkupId(true);
		return checkGroup;
	}

	/**
	 * Factory method for create a new {@link CheckGroupSelector}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @return the new {@link CheckGroupSelector}
	 */
	public static <T> CheckGroupSelector newCheckGroupSelector(final String id)
	{
		CheckGroupSelector checkGroupSelector = new CheckGroupSelector(id);
		checkGroupSelector.setOutputMarkupId(true);
		return checkGroupSelector;
	}

	/**
	 * Factory method for create a new {@link CheckGroupSelector}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param group
	 *            the {@link CheckGroup}
	 * @return the new {@link CheckGroupSelector}
	 */
	public static <T> CheckGroupSelector newCheckGroupSelector(final String id,
		final CheckGroup<T> group)
	{
		CheckGroupSelector checkGroupSelector = new CheckGroupSelector(id, group);
		checkGroupSelector.setOutputMarkupId(true);
		return checkGroupSelector;
	}

	/**
	 * Factory method for create a new {@link WebMarkupContainer}.
	 *
	 * @param id
	 *            the id
	 * @return the new {@link WebMarkupContainer}
	 */
	public static WebMarkupContainer newWebMarkupContainer(final String id)
	{
		return newWebMarkupContainer(id, null);
	}

	/**
	 * Factory method for create a new {@link WebMarkupContainer}.
	 *
	 * @param <T>
	 *            the generic type of the model
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new {@link WebMarkupContainer}
	 */
	public static <T> WebMarkupContainer newWebMarkupContainer(final String id,
		final IModel<T> model)
	{
		WebMarkupContainer webMarkupContainer = new WebMarkupContainer(id, model);
		webMarkupContainer.setOutputMarkupId(true);
		return webMarkupContainer;
	}

}
