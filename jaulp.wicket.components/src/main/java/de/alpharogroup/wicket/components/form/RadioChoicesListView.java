package de.alpharogroup.wicket.components.form;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A list view that renders a list of radio buttons with appropriate labels
 * based on an {@link IChoiceRenderer}. When used in combination with a
 * {@link org.apache.wicket.markup.html.form.RadioGroup}, this a good
 * alternative to Wicket's built-in
 * {@link org.apache.wicket.markup.html.form.RadioChoice},
 * because it gives you full control over the markup and is extensible.
 * <p>
 * Your markup must contain the following:
 * <ul>
 * <li>{@code <input type="radio" wicket:id="radio" />} where you
 *     want the radio button to appear.</li>
 * <li>A component with {@code wicket:id="label"} where you want the display
 *     value to appear.</li>
 * </ul>
 * <p>
 * For example:
 * <pre class="example">
 * &lt;wicket:container wicket:id="group"&gt;
 *   &lt;label wicket:id="choices"&gt;
 *     &lt;input type="radio" wicket:id="radio" /&gt;
 *     &lt;wicket:container wicket:id="label"&gt;Label&lt;/wicket:container&gt;
 *   &lt;/label&gt;
 * &lt;/wicket:container&gt;
 * 
 * add(new RadioGroup("group", selectedItemModel)
 *     .add(new RadioChoicesListView("choices", choicesModel, renderer)));</pre>
 * <p>
 * You can also override {@link #populateItem(ListItem) populateItem()}
 * if you want to display additional data per radio choice, like a 
 * description paragraph.
 * 
 * This class is inspired from fiftyfive.wicket.core project. Some changes with the generic types was done.
 * For more information read this blog: <a href="http://blog.55minutes.com/2011/10/how-to-implement-radio-buttons-in-wicket/">http://blog.55minutes.com/2011/10/how-to-implement-radio-buttons-in-wicket/</a>
 */
public class RadioChoicesListView<T> extends ChoicesListView<T>
{
    /**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public RadioChoicesListView(String id,
                                IModel<List<T>> choices,
                                IChoiceRenderer<T> renderer)
    {
        super(id, choices, renderer);
    }
    
	protected void populateItem(ListItem<T> it)
    {
        final int index = it.getIndex();
        it.add(new Label("label", getChoiceLabel(it.getModelObject())));
        it.add(new Radio<T>("radio", it.getModel()) {
            
            /**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public String getValue()
            {
                return getChoiceValue(getModelObject(), index);
            }
            
            @Override
            public IModel<String> getLabel()
            {
                return Model.of(getChoiceLabel(getModelObject()));
            }
            
            @Override
            protected boolean getStatelessHint()
            {
                return true;
            }
        });
    }
}