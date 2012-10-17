package org.dropdownchoices.examples;

import java.io.IOException;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.jaulp.wicket.PackageResourceReferences;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see wicket.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
    @Override
	public Session newSession(Request request, Response response) {
		return new WicketSession(request);
	}

	/**
     * Constructor
     */
	public WicketApplication()
	{
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
    	prr.initializeResources("org");
    }
	/**
	 * @see wicket.Application#getHomePage()
	 */
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}



}
