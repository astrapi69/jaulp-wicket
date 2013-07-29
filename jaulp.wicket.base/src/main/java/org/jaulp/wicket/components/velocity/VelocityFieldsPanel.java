package org.jaulp.wicket.components.velocity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jaulp.xml.tag.Tag;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.velocity.markup.html.VelocityPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class VelocityFieldsPanel.
 */
public class VelocityFieldsPanel extends Panel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new velocity fields panel.
	 *
	 * @param id the id
	 * @param model the model
	 */
	public VelocityFieldsPanel(String id, final IModel<List<WicketField<?>>> model) {
		super(id, model);
		final Map<String, List<WicketField<?>>> map = new HashMap<String, List<WicketField<?>>>();
		map.put("fields", model.getObject());
		StringBuilder sb = new StringBuilder();

		for (Tag tag : model.getObject()) {
			sb.append(tag.toString());
		}
		String tmp = sb.toString();

		final IResourceStream template = new StringResourceStream(tmp);
		VelocityPanel velocityPanel = new VelocityPanel("velocityPanel",
				new MapModel<String, List<WicketField<?>>>(map)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected IResourceStream getTemplateResource() {
				return template;
			}

			@Override
			protected boolean parseGeneratedMarkup() {
				return true;
			}
		};
		add(velocityPanel);
		for (WicketField<?> field : model.getObject()) {
			addChildComponent(field);
			velocityPanel.add(field.getComponent());
		}
	}
	
	/**
	 * Adds the child component.
	 *
	 * @param parent the parent
	 */
	public void addChildComponent(WicketField<?> parent) {
		if(parent.getChildren() != null && !parent.getChildren().isEmpty()){
			for (Tag iterable_element : parent.getChildren()) {
				WicketField<?> field = (WicketField<?>) iterable_element;
				Component c = parent.getComponent();
				if(c instanceof MarkupContainer) {
					MarkupContainer mc = (MarkupContainer) c;
					mc.add(field.getComponent());
				}
				addChildComponent(field);
			}
		}		
	}

	/**
	 * Instantiates a new velocity fields panel.
	 *
	 * @param id the id
	 * @param model the model
	 * @param templateResource the template resource
	 */
	@SuppressWarnings("rawtypes")
	public VelocityFieldsPanel(String id, final IModel<? extends Map> model, final IResourceStream templateResource){
		super(id, model);		
		add(VelocityPanel.forTemplateResource("velocityPanel", model, templateResource));
	}

}
