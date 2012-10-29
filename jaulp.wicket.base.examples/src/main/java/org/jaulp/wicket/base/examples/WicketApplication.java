package org.jaulp.wicket.base.examples;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.wicket.protocol.http.WebApplication;
import org.jaulp.wicket.PackageResourceReferences;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.jaulp.wicket.base.examples.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/** The Constant logger. */
	private static final Logger logger = Logger
			.getLogger(WicketApplication.class.getName());
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		initializeAllHeaderContributors();
		// add your configuration here
	}
	
	/**
	 * Initialize all header contributors.
	 */
	private void initializeAllHeaderContributors() {
		try {
			initializeResources();
		} catch (final ClassNotFoundException e) {
			logger.error(
					"ClassNotFoundException in the initializeResources-Method from the WicketApplication.",
					e);
		} catch (final IOException e) {
			logger.error(
					"IOException in the initializeResources-Method from the WicketApplication.",
					e);
		}
	}

	/**
	 * Initialize resources.
	 *
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void initializeResources() throws ClassNotFoundException,
			IOException {
		PackageResourceReferences prr = PackageResourceReferences.getInstance();
    	prr.initializeResources("org.jaulp.wicket");
	}
}
