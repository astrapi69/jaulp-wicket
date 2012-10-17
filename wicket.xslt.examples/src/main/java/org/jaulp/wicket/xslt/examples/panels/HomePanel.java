package org.jaulp.wicket.xslt.examples.panels;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.transformer.XsltTransformerBehavior;
import org.apache.wicket.model.Model;


public class HomePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HomePanel(final String id) {
		super(id);
		Label report = new Label("message", new Model<String>(
				
						 "<message>Yep, it worked!</message>"));
		add(report);
		report.setEscapeModelStrings(false);
		XsltTransformerBehavior trans = new XsltTransformerBehavior("def.xsl");
		trans.bind(report);
		report.add(trans);
	}
}