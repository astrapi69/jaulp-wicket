package de.alpharogroup.wicket.components.examples.termofuse;
import org.apache.wicket.model.IModel;

import de.alpharogroup.wicket.components.termofuse.TermOfUseModel;
import de.alpharogroup.wicket.components.termofuse.TermOfUsePanel;


public class ApplicationTermOfUsePanel extends TermOfUsePanel {
	
	private static final long serialVersionUID = 1L;

	public ApplicationTermOfUsePanel(String id, IModel<TermOfUseModel> model) {
		super(id, model);
	}
}