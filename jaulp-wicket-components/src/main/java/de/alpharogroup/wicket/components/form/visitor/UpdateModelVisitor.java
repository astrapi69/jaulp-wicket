package de.alpharogroup.wicket.components.form.visitor;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IFormModelUpdateListener;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

/**
 * The class {@link UpdateModelVisitor} can be added to a form for update the
 * component models even if an validation error occurs in the given form.
 *
 * <pre>
 * Form form = new Form()
 * {
 *  ...
 *	protected void onError() {
 *		super.onError();
 *		visitFormComponents(new UpdateModelVisitor(this));
 *	}
 * };
 * </pre>
 *
 */
public class UpdateModelVisitor implements IVisitor<FormComponent<?>, Void>
{

	/** The form. */
	private final Form<?> form;

	/**
	 * Instantiates a new {@link UpdateModelVisitor}.
	 *
	 * @param form
	 *            the form
	 */
	public UpdateModelVisitor(final Form<?> form) {
		this.form = form;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void component(final FormComponent<?> formComponent, final IVisit<Void> visit) {
		if (formComponent instanceof IFormModelUpdateListener) {
			final Form<?> form = Form.findForm(formComponent);
			if (form != null) {
				if (this.form == null || this.form == form) {
					if (form.isEnabledInHierarchy()) {
						if (formComponent.isVisibleInHierarchy() && formComponent.isEnabledInHierarchy()) {
							formComponent.modelChanging();
							((IFormModelUpdateListener) formComponent).updateModel();
							formComponent.modelChanged();
						}
					}
				}
			}
		}
	}
}