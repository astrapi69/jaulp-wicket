package de.alpharogroup.wicket.components.examples.ajaxtabs.tabpanels;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

public class TabModel<T> implements Serializable
{

	private static final long serialVersionUID = 1L;
	private IModel<String> title;
	private IModel<T> content;
	private IModel<String> closeTitle;

	public IModel<String> getCloseTitle()
	{
		return closeTitle;
	}

	public void setCloseTitle(IModel<String> closeTitle)
	{
		this.closeTitle = closeTitle;
	}

	public TabModel()
	{
	}

	public TabModel(IModel<String> title, IModel<T> content, final IModel<String> closeTitle)
	{
		this.title = title;
		this.content = content;
		this.closeTitle = closeTitle;
	}

	public IModel<String> getTitle()
	{
		return title;
	}

	public void setTitle(IModel<String> title)
	{
		this.title = title;
	}

	public IModel<T> getContent()
	{
		return content;
	}

	public void setContent(IModel<T> content)
	{
		this.content = content;
	}
}
