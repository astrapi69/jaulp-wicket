package de.alpharogroup.wicket.components.form.checkbox;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.base.BasePanel;

public class CheckGroupSelectorPanel<T> extends BasePanel<CheckboxModel<T>> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public CheckGroupSelectorPanel(String id, IModel<CheckboxModel<T>> model) {
		super(id, model);
		Form<?> form = new Form<>("form");
		add(form);
		
		CheckGroup<T> checkGroup = new CheckGroup<T>("checkGroup", new PropertyModel<List<T>>(model.getObject(), "selectedItems"));
		checkGroup.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;
			protected void onUpdate(AjaxRequestTarget target) {
				CheckGroupSelectorPanel.this.onUpdate(target);
			}
		});
		form.add(checkGroup);
		checkGroup.add(new CheckGroupSelector("groupselector"));
		checkGroup.add(new Label("groupselectorLabel", "check/uncheck all"));
		ListView<T> choices = new ListView<T>("choices", model.getObject().getChoices()) {	   
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<T> item) {
                item.add(new Check<T>("checkbox", item.getModel()));
                item.add(new Label("label", new PropertyModel<String>(item.getDefaultModel(), "name")));
			}
        };
        choices.setReuseItems(true);
        checkGroup.add(choices);
	}
	
	protected void onUpdate(AjaxRequestTarget target) {		
	}

}
