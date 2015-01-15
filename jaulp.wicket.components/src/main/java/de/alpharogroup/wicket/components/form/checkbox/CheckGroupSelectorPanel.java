package de.alpharogroup.wicket.components.form.checkbox;

import java.util.Collection;
import java.util.List;

import lombok.Getter;

import org.apache.wicket.Component;
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
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.jaulp.wicket.base.BasePanel;

import de.alpharogroup.wicket.components.factory.ComponentFactory;

public abstract class CheckGroupSelectorPanel<T> extends BasePanel<CheckboxModel<T>> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the form.
	 */
	@Getter
	private Form<?> form;	

	@Getter
	private CheckGroup<T> checkGroup;	

	@Getter
	private CheckGroupSelector checkGroupSelector;

	@Getter
	private Label checkGroupSelectorLabel;
	
	@Getter
	private Component choices;

	public CheckGroupSelectorPanel(String id, IModel<CheckboxModel<T>> model) {
		super(id, model);
		
		add(form = newForm("form"));
		
		form.add(checkGroup = newCheckGroup("checkGroup", new PropertyModel<List<T>>(model.getObject(), "selectedItems")));
		
		checkGroup.add(checkGroupSelector = newCheckGroupSelector("checkGroupSelector", checkGroup));
		
		checkGroup.add(checkGroupSelectorLabel = newCheckGroupSelectorLabel("checkGroupSelectorLabel", checkGroupSelector.getMarkupId(), Model.of("check/uncheck all")));
		
        checkGroup.add(choices = newChoices("choices", model));
	}
	
	protected Component newChoices(final String id, final IModel<CheckboxModel<T>> model) {
		ListView<T> choices = new ListView<T>("choices", model.getObject().getChoices()) {	   
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<T> item) {
                item.add(new Check<T>("checkbox", item.getModel()));
                item.add(new Label("label", new PropertyModel<String>(item.getDefaultModel(),  model.getObject().getLabelPropertyExpression())));
			}
        };
        choices.setReuseItems(true);
        return choices;
	}
	
	protected Label newCheckGroupSelectorLabel(final String id, final String forId, final IModel<String> model) {
		return ComponentFactory.newLabel(id, forId, model);
	}
	
	/**
	 * Factory method for create a new {@link CheckGroupSelector}.
	 *
	 * @param id the id
	 * @param group the {@link CheckGroup}
	 * @return the new {@link CheckGroupSelector}
	 */
	protected CheckGroupSelector newCheckGroupSelector(final String id, final CheckGroup<T> group) {
		return ComponentFactory.newCheckGroupSelector(id, group);
	}
	
	/**
	 * New form.
	 *
	 * @param id the id
	 * @return the form
	 */
	protected Form<?> newForm(final String id) {
		return ComponentFactory.newForm(id);
	}
	
	/**
	 * New check group.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the radio group
	 */
	protected CheckGroup<T> newCheckGroup(final String id, final IModel<? extends Collection<T>> model) {
		CheckGroup<T> checkGroup = ComponentFactory.newCheckGroup(id, model);
		checkGroup.add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;
			protected void onUpdate(AjaxRequestTarget target) {
				CheckGroupSelectorPanel.this.onUpdate(target);
			}
		});
		return checkGroup;
	}
	
	protected abstract void onUpdate(AjaxRequestTarget target);
}
