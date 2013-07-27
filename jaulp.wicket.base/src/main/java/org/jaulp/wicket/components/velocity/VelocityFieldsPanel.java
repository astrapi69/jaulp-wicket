package org.jaulp.wicket.components.velocity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.jaulp.xml.tag.Tag;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.velocity.markup.html.VelocityPanel;

public class VelocityFieldsPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			velocityPanel.add(field.getComponent());
		}
	}

}
