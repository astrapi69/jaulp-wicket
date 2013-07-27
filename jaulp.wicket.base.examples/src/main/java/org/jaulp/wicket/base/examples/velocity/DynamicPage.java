package org.jaulp.wicket.base.examples.velocity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jaulp.wicket.base.examples.MenubarPanel;
import org.jaulp.wicket.components.velocity.VelocityFieldsPanel;
import org.jaulp.wicket.components.velocity.WicketField;

public class DynamicPage extends WebPage {
	private static final String WICKET_ID = "wicket:id";

	private static final long serialVersionUID = 1L;

	private List<WicketField<?>> fields;

	public DynamicPage(final PageParameters parameters) {
		super(parameters);
		add(new MenubarPanel("menubarPanel"));
		init();
		IModel<List<WicketField<?>>> m = new CompoundPropertyModel<List<WicketField<?>>>(fields);
		VelocityFieldsPanel velocityFieldsPanel = new VelocityFieldsPanel("velocityFieldsPanel", m);
		add(velocityFieldsPanel);
	}

	public void init() {
		fields = new ArrayList<WicketField<?>>();
		Map<String, String> labelAttributes = new LinkedHashMap<String, String>();
		labelAttributes.put(WICKET_ID, "textLabel");

		WicketField<Label> labelWicketField = new WicketField<Label>();
		labelWicketField.setAttributes(labelAttributes);
		Label label = new Label(labelWicketField.getAttributes().get(WICKET_ID)	);
		labelWicketField.setComponent(label);
		labelWicketField.getComponent().setDefaultModel(Model.of("Name:"));
		labelWicketField.setName("label");
		labelWicketField.setEndTag(true);
		fields.add(labelWicketField);

		Map<String, String> inputAttributes = new LinkedHashMap<String, String>();
		inputAttributes.put(WICKET_ID, "inputLabel");
		inputAttributes.put("type", "text");

		WicketField<TextField<String>> textWicketField = new WicketField<TextField<String>>();
		textWicketField.setAttributes(inputAttributes);
		textWicketField.setComponent(new TextField<String>(textWicketField
				.getAttributes().get(WICKET_ID)));
		textWicketField.getComponent().setDefaultModel(Model.of(""));
		textWicketField.setName("input");
		textWicketField.setEndTag(false);
		fields.add(textWicketField);

	}
}
