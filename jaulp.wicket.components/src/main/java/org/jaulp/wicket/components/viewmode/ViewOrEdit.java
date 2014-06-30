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
package org.jaulp.wicket.components.viewmode;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.AbstractChoice;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;
import org.apache.wicket.model.Model;

/**
 * The Class ViewOrEdit is adapted from the visural-wicket project. The class is
 * respective adjusted for commonly use.
 */
public class ViewOrEdit<T> extends FormComponentPanel<T> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/**
	 * This is the id of the component to be contained within the ViewOrEdit
	 * container. Any component provided to the ViewOrEdit component should be
	 * identified as ViewOrEdit.COMP_ID
	 */
	public final static String COMP_ID = "component";

	/** The component. */
	private final FormComponent<T> component;

	/** The label. */
	private Label label;

	/**
	 * Instantiates a new view or edit.
	 * 
	 * @param id
	 *            the id
	 * @param component
	 *            the component
	 */
	public ViewOrEdit(String id, FormComponent<T> component) {
		this(id, component, null, null);
	}

	/**
	 * Instantiates a new view or edit.
	 * 
	 * @param id
	 *            the id
	 * @param component
	 *            the component
	 * @param labelModel
	 *            the label model
	 */
	public ViewOrEdit(String id, FormComponent<T> component,
			IModel<?> labelModel) {
		this(id, component, labelModel, null);
	}

	/**
	 * Instantiates a new view or edit.
	 * 
	 * @param id
	 *            the id
	 * @param component
	 *            the component
	 * @param componentModelToLabel
	 *            the component model to label
	 */
	public ViewOrEdit(String id, FormComponent<T> component,
			ComponentModelToLabel<T> componentModelToLabel) {
		this(id, component, null, componentModelToLabel);
	}

	/**
	 * Instantiates a new view or edit.
	 * 
	 * @param id
	 *            the id
	 * @param component
	 *            the component
	 * @param labelModel
	 *            the label model
	 * @param componentModelToLabel
	 *            the component model to label
	 */
	protected ViewOrEdit(final String id, final FormComponent<T> component,
			IModel<?> labelModel,
			final ComponentModelToLabel<T> componentModelToLabel) {
		super(id);
		this.component = component;

		if (labelModel == null && componentModelToLabel != null) {
			labelModel = new AbstractReadOnlyModel<String>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				private final ComponentModelToLabel<T> converter = componentModelToLabel;

				@Override
				public String getObject() {
					return converter.convertToLabel(component.getModelObject());
				}
			};
		}

		if (component == null || !component.getId().equals(COMP_ID)) {
			throw new IllegalArgumentException(
					"The component provided to LabelOnViewOnly(...) must have the id \""
							+ COMP_ID + "\"");
		}

		Fragment f = resolveComponentFragment(labelModel);
		if (f == null) {
			throw new UnsupportedOperationException(
					"No view mode fragment for component of type "
							+ component.getClass().getName());
		}
		add(f);
	}

	/**
	 * Provide support for using CompountPropertyModel or similar on form and
	 * having that property model chain to the form component being wrapped in a
	 * ViewOrEdit.
	 * 
	 * @return the i model
	 */
	@Override
	protected IModel<?> initModel() {
		final IModel<?> parentModel = super.initModel();
		if (parentModel != null && parentModel instanceof IWrapModel) {
			// we want to set this model to wrap the form component
			return new WrappedParentModel((IWrapModel<?>) parentModel);
		}
		// we do this in case there is no parent model to avoid
		// "Attempt to set model object on null model of component" errors
		return Model.of("");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.markup.html.form.FormComponent#convertInput()
	 */
	@Override
	protected void convertInput() {
		// this forces a call to initModel()
		getDefaultModel();
	}

	/**
	 * Determine whether the component is in view mode or not.
	 * 
	 * By default, the implementation will assume view mode when this component
	 * is not enabled or the form component is not enabled or the form itself is
	 * not enabled, however this behaviour may be overriden as req'd.
	 * 
	 * @return true, if is view mode
	 */
	public boolean isViewMode() {
		return !this.isEnabled() || !component.isEnabled()
				|| !component.getForm().isEnabled();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.Component#onBeforeRender()
	 */
	@Override
	protected void onBeforeRender() {
		// this forces a call to initModel()
		getDefaultModel();
		// now proceed as normal
		super.onBeforeRender();
		boolean isView = this.isViewMode();
		label.setVisible(isView);
		component.setVisible(!isView);
	}

	/**
	 * Resolve component fragment.
	 * 
	 * @param labelModel
	 *            the label model
	 * @return the fragment
	 */
	private Fragment resolveComponentFragment(IModel<?> labelModel) {
		if (labelModel == null) {
			// TODO: rather than doing this, maybe lookup converter?
			labelModel = new IModel<Object>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public Object getObject() {
					return (component.getModelObject() == null ? null
							: component.getModelObject().toString());
				}

				public void setObject(Object arg0) {
				}

				public void detach() {
				}
			};
		}
		label = new Label("viewLabel", labelModel);
		label.setEscapeModelStrings(isEscapeLabelModelStrings());
		if (TextField.class.isAssignableFrom(component.getClass())) {
			Fragment f = new Fragment("controlPair", "textfield", this);
			f.add(label);
			f.add(component);
			return f;
		}
		if (CheckBox.class.isAssignableFrom(component.getClass())) {
			Fragment f = new Fragment("controlPair", "checkbox", this);
			f.add(label);
			f.add(component);
			return f;
		}
		if (TextArea.class.isAssignableFrom(component.getClass())) {
			Fragment f = new Fragment("controlPair", "textarea", this);
			f.add(label);
			f.add(component);
			return f;
		}
		if (AbstractChoice.class.isAssignableFrom(component.getClass())) {
			Fragment f = new Fragment("controlPair", "choice", this);
			f.add(label);
			f.add(component);
			return f;
		}
		return null;
	}

	/**
	 * Gets the component.
	 * 
	 * @return the component
	 */
	public FormComponent<T> getComponent() {
		return component;
	}

	/**
	 * Checks if is escape label model strings.
	 * 
	 * @return true, if is escape label model strings
	 */
	public boolean isEscapeLabelModelStrings() {
		return false;
	}

	/**
	 * The Class WrappedParentModel.
	 */
	class WrappedParentModel implements IComponentInheritedModel {

		/** The real parent. */
		private final IWrapModel realParent;

		/**
		 * Instantiates a new wrapped parent model.
		 * 
		 * @param realParent
		 *            the real parent
		 */
		public WrappedParentModel(IWrapModel realParent) {
			this.realParent = realParent;
		}

		public IWrapModel wrapOnInheritance(Component arg0) {
			if (arg0 == ViewOrEdit.this.component) {
				return realParent;
			} else {
				return null;
			}
		}

		public Object getObject() {
			return null;
		}

		public void setObject(Object arg0) {
		}

		public void detach() {
		}
	}

}
