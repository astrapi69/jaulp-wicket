package org.jaulp.wicket.data.provider.examples;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see StartDataProviderExample.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class<? extends Page> getHomePage()
	{
		return HomePage.class;
	}

}
