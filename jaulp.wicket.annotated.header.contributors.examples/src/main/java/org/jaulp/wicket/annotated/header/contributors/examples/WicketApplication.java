package org.jaulp.wicket.annotated.header.contributors.examples;

import java.io.IOException;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.jaulp.wicket.PackageResourceReferences;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see wicket.myproject.Start#main(String[])
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
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

    /**
     * {@inheritDoc}
     * @see org.apache.wicket.protocol.http.WebApplication#init()
     */
    @Override
	protected void init() {
		super.init();
        try {
        	initResources();
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
	}


    /**
     * Inits the all relevant resources like css and js files.
     *
     * @throws ClassNotFoundException the class not found exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void initResources() throws ClassNotFoundException, IOException {
    	PackageResourceReferences prr = PackageResourceReferences.getInstance();
    	prr.initializeResources("org.jaulp");
    }

}
