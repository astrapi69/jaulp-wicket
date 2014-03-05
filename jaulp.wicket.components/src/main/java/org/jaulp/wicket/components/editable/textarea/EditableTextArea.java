package org.jaulp.wicket.components.editable.textarea;

import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * An editable TextArea that can be switched to a MultilineLabel.
 *
 * @author Asterios Raptis
 */
public class EditableTextArea extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The flag editable. */
	private boolean editable;

	/**
	 * Checks if is editable.
	 *
	 * @return true, if it is editable
	 */
	public boolean isEditable() {
		return editable;
	}
	
	/** The MultiLineLabel. */
	private final MultiLineLabel label;
	
	/** The text area. */
	private final TextArea<String> textArea;

	/**
	 * Sets the editable.
	 *
	 * @param editable the new editable
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Instantiates a new editable text area.
	 *
	 * @param id the id
	 * @param model the model
	 * @see org.apache.wicket.Component#Component(String, IModel)
	 */
	public EditableTextArea(final String id, final IModel<String> model) {
		super(id, model);
		editable = true;
		this.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		add(label = newMultiLineLabel("label", model));
		add(textArea = newTextArea("textarea", model));
	}



	/**
	 * Factory method for creating the TextArea. This method is invoked in the
	 * constructor from this class and can be overridden so users can
	 * provide their own version of a TextArea.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the text area
	 */
	protected TextArea<String> newTextArea(String id, IModel<String> model) {
		TextArea<String> textArea = new TextArea<String>(id, model){
			private static final long serialVersionUID = 1L;
			@Override
		    protected void onConfigure() {
		        setVisibilityAllowed(isEditable());
		    }
		};
		textArea.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		return textArea;
	}
	
	/**
	 * Factory method for creating the MultiLineLabel. This method is invoked in the
	 * constructor from the derived classes and can be overridden so users can
	 * provide their own version of a MultiLineLabel.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the MultiLineLabel
	 */
	protected MultiLineLabel newMultiLineLabel(String id, IModel<String> model) {
		MultiLineLabel multiLineLabel = new MultiLineLabel(id, model){
			private static final long serialVersionUID = 1L;
			@Override
		    protected void onConfigure() {
		        setVisibilityAllowed(!isEditable());
		    }
		};
		multiLineLabel.setOutputMarkupId(true).setOutputMarkupPlaceholderTag(true);
		return multiLineLabel;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public MultiLineLabel getLabel() {
		return label;
	}

	/**
	 * Gets the text area.
	 *
	 * @return the text area
	 */
	public TextArea<String> getTextArea() {
		return textArea;
	}

}