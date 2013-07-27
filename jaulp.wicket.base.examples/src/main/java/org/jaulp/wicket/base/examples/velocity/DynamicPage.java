package org.jaulp.wicket.base.examples.velocity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.jaulp.xml.tag.Tag;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.velocity.markup.html.VelocityPanel;
import org.jaulp.wicket.base.examples.MenubarPanel;

public class DynamicPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	private Map<Tag, WicketField<?,?,?>> tagWicketFieldsMap;

    public DynamicPage(final PageParameters parameters) {
	super(parameters);
	
	add(new MenubarPanel("menubarPanel"));
	init();
	 VelocityPanel panel;
	 
	 Set<Tag> tags =tagWicketFieldsMap.keySet();
	 List<Tag> tagList = new ArrayList<Tag>(tags);
	 
	 List<WicketField<?,?,?>> fields = new ArrayList<WicketField<?,?,?>>( tagWicketFieldsMap.values());
	 final Map<String, List<Tag>> map = new HashMap<String, List<Tag>>();
	 map.put("fields", tagList);
	 StringBuilder sb = new StringBuilder();
	 
	 for (Tag tag : tags) {
		sb.append(tag.toString());
	}
	 String tmp = sb.toString();
	 
	 final IResourceStream template = new StringResourceStream(tmp); 
	 
	 add(panel = new VelocityPanel("templatePanel", new MapModel<String, List<Tag>>(map))
     {
         /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
         protected IResourceStream getTemplateResource()
         {
             return template;
         }

         @Override
         protected boolean parseGeneratedMarkup()
         {
             return true;
         }
     });
     for (WicketField<?,?,?> field : fields)
     {
         panel.add(field.getComponent());
     }
    }
    
    public void init(){
		tagWicketFieldsMap = new LinkedHashMap<Tag, WicketField<?,?,?>>();
		Map<String, String> labelAttributes = new LinkedHashMap<String, String>();
		labelAttributes.put("wicket:id", "textLabel");
		Tag tag = new Tag();
		tag.setName("label");
		tag.setEndTag(true);
		tag.setAttributes(labelAttributes);
		
		WicketField<Label, Void, IModel<String>> labelWicketField = new WicketField<Label, Void, IModel<String>>();
		labelWicketField.setModel(Model.of("Name:"));
		Label label = new Label(tag.getAttributes().get("wicket:id"), labelWicketField.getModel());
		labelWicketField.setComponent(label);
		labelWicketField.setWicketTag(tag);
		
		tagWicketFieldsMap.put(tag, labelWicketField);
		
		Map<String, String> inputAttributes = new LinkedHashMap<String, String>();
		inputAttributes.put("wicket:id", "inputLabel");
		inputAttributes.put("type", "text");
		Tag inputTag = new Tag();
		inputTag.setName("input");
		inputTag.setEndTag(false);
		inputTag.setAttributes(inputAttributes);
		
		WicketField<TextField<String>, String, IModel<String>> textWicketField = new WicketField<TextField<String>, String, IModel<String>>();
		textWicketField.setModel(Model.of(""));
		textWicketField.setComponent(new TextField<String>(inputTag.getAttributes().get("wicket:id"), textWicketField.getModel()));
		textWicketField.setWicketTag(inputTag);
		
		tagWicketFieldsMap.put(inputTag, textWicketField);
    }
}
