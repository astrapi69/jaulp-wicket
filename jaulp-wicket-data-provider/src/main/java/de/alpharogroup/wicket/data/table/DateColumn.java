package de.alpharogroup.wicket.data.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * The class {@link DateColumn} can format a date.
 *
 * @param <T>
 *            the generic type from the model object
 */
public class DateColumn<T> extends PropertyColumn<T, Void>
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The date format. */
	private final String dateFormat;

	/**
	 * Instantiates a new {@link DateColumn} object.
	 *
	 * @param displayModel
	 *            the display model
	 * @param propertyExpression
	 *            the property expression
	 * @param dateFormat
	 *            the date format
	 */
	public DateColumn(final IModel<String> displayModel, final String propertyExpression,
		final String dateFormat)
	{
		super(displayModel, propertyExpression);
		this.dateFormat = Args.notNull(dateFormat, "dateFormat");
	}

	/**
	 * {@inheritDoc}
	 **/
	@Override
	public void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId,
		final IModel<T> rowModel)
	{
		cellItem.add(new Label(componentId, new AbstractReadOnlyModel<String>()
		{
			private static final long serialVersionUID = 1L;

			/** * {@inheritDoc} */
			@Override
			public String getObject()
			{
				return onGetObject(rowModel);
			}
		}));
	}

	/**
	 * Callback method for provide specific behavior on method {@link AbstractReadOnlyModel#getObject()}.
	 *
	 * @param rowModel the row model
	 * @return the string
	 */
	protected String onGetObject(final IModel<T> rowModel)
	{
		final IModel<?> propertyModel = DateColumn.this.getDataModel(rowModel);
		final Date value = (Date)propertyModel.getObject();
		final SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(value);
	}
}