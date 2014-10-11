package de.alpharogroup.wicket.components.examples.ajaxtabs.dynamic;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.TextField;

public class UpdatingTextField<T> extends TextField<T> {
	private static final long serialVersionUID = 1L;

	public UpdatingTextField(String id) {
		super(id);
		add(new AjaxFormComponentUpdatingBehavior("onblur") {
			private static final long serialVersionUID = 1L;

			protected void onUpdate(AjaxRequestTarget target) {
			}
		});
	}
}