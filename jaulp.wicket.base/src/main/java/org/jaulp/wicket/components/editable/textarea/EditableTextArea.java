package org.jaulp.wicket.components.editable.textarea;

import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.jaulp.wicket.base.utils.RemoveCssClass;

/**
 * Multi-row text editing component that can be switched to a viewable component so it can be not editable.
 * 
 * @author Asterios Raptis
 * 
 * @param <T>
 *            The model object type
 */
@ImportResources(resources = {})
public class EditableTextArea<T> extends TextArea<T> {
	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forReference(new CssResourceReference(EditableTextArea.class, "EditableTextArea.css")));
		super.renderHead(response);
	}

	private static final long serialVersionUID = 1L;

	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		if(!this.editable){
			// add class for not editable textarea
			add(new AttributeAppender("class", new Model<String>("uneditable"), " "));
			this.setEnabled(false);
		} else {
			// remove class for not editable textarea
			add(new RemoveCssClass("uneditable"));
			this.setEnabled(true);
		}
	}

	/**
	 * @see org.apache.wicket.Component#Component(String)
	 */
	public EditableTextArea(final String id) {
		super(id);
	}

	/**
	 * @param id
	 * @param model
	 * @see org.apache.wicket.Component#Component(String, IModel)
	 */
	public EditableTextArea(final String id, final IModel<T> model) {
		super(id, model);
	}

}