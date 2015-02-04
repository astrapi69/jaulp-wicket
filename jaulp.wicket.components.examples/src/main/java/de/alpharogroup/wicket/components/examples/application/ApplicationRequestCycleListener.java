package de.alpharogroup.wicket.components.examples.application;

import org.apache.wicket.request.component.IRequestablePage;
import org.jaulp.wicket.base.application.AbstractApplicationRequestCycleListener;

import de.alpharogroup.wicket.components.examples.exceptions.ExceptionPage;

public class ApplicationRequestCycleListener extends AbstractApplicationRequestCycleListener
{
	private static final long serialVersionUID = 1L;

	public IRequestablePage getExceptionPage(Exception e)
	{
		e.printStackTrace();
		return new ExceptionPage(e);
	}
}