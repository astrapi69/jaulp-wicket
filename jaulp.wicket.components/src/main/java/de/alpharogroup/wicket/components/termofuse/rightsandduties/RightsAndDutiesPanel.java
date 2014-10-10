package de.alpharogroup.wicket.components.termofuse.rightsandduties;

import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.util.resource.ResourceModelFactory;
import org.jaulp.wicket.behaviors.AddJsQueryBehavior;
import de.alpharogroup.wicket.components.i18n.list.HeaderContentListPanel;
import de.alpharogroup.wicket.components.i18n.list.UnorderedListPanel;
import de.alpharogroup.wicket.components.i18n.list.DivListPanel;


public class RightsAndDutiesPanel extends Panel {

	private static final long serialVersionUID = 1L;	

	public RightsAndDutiesPanel(String id) {
		this(id, null);
	}

	public RightsAndDutiesPanel(String id, IModel<RightsAndDutiesModel> model) {
		super(id, model);
		
		add(new HeaderContentListPanel("introduction", Model.of(model.getObject().getIntroductionModel())) {

			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item) {
				return new Label(id, newContentResourceModel(item.getModel())).add(new AddJsQueryBehavior("wrap", "<p></p>"));
			}

			@Override
			protected Component newHeaderLabel(String id, IModel<String> model) {
				return super.newHeaderLabel(id, model).add(new AddJsQueryBehavior("wrap", "<h2></h2>"));
			}
		});
		
		add(new UnorderedListPanel("list", Model.of(model.getObject().getListModel())) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item) {
				return new Label(id, ResourceModelFactory.newResourceModel(item.getModel().getObject(), this));
			}
		});
		
		add(new DivListPanel("summary", Model.of(model.getObject().getSummaryModel())) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newListComponent(String id, ListItem<ResourceBundleKey> item) {
				return new Label(id, ResourceModelFactory.newResourceModel(item.getModel().getObject(), this)).add(new AddJsQueryBehavior("wrap", "<p></p>"));
			}
		});

	}

}
