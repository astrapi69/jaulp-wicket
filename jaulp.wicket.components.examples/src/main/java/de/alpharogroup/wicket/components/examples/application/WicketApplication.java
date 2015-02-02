package de.alpharogroup.wicket.components.examples.application;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.util.ApplicationUtils;

import de.alpharogroup.wicket.bootstrap3.application.WicketBootstrap3Application;
import de.alpharogroup.wicket.components.examples.home.HomePage;


/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see de.alpharogroup.wicket.components.examples.StartComponentExamples#main(String[])
 */
public class WicketApplication extends WicketBootstrap3Application
{    	
    public static final int DEFAULT_HTTP_PORT = 9090;
    public static final int DEFAULT_HTTPS_PORT = 9443;
	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(WicketApplication.class.getName());

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
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
		// initialize all header contributors
		initializeAllHeaderContributors();
		// set footer scripts...
		ApplicationUtils.setHeaderResponseDecorator(this, FOOTER_FILTER_NAME);
		// set up ports for http and https...
		ApplicationUtils.setRootRequestMapper(this, getHttpPort(), getHttpsPort());
		// set exception handling for error page...
		ApplicationUtils.setExceptionSettingsForDeployment(this, new ApplicationRequestCycleListener());
		// add file patterns to the resource guard...
		ApplicationUtils.addFilePatternsToPackageResourceGuard(this, "+*.css", "+*.png");
	}
	
	protected int getHttpPort() {
		if(getProperties().containsKey("application.http.port")) {
			String httpPortString = getProperties().getProperty("application.http.port");			
			try {
				int httpPort = Integer.valueOf(httpPortString);
				return httpPort;
			} catch (NumberFormatException e) {
				return WicketApplication.DEFAULT_HTTP_PORT;
			}
		}
		return WicketApplication.DEFAULT_HTTP_PORT;
	}
	
	protected int getHttpsPort() {
		if(getProperties().containsKey("application.https.port")) {
			String httpsPortString = getProperties().getProperty("application.https.port");			
			try {
				int httpsPort = Integer.valueOf(httpsPortString);
				return httpsPort;
			} catch (NumberFormatException e) {
				return WicketApplication.DEFAULT_HTTPS_PORT;
			}
		}
		return WicketApplication.DEFAULT_HTTPS_PORT;
	}

	/**
	 * Initialize all header contributors.
	 */
	private void initializeAllHeaderContributors() {
		try {
			initializeResources();
		} catch (final ClassNotFoundException e) {
			LOGGER.error(
					"ClassNotFoundException in the initializeResources-Method from the WicketApplication.",
					e);
		} catch (final IOException e) {
			LOGGER.error(
					"IOException in the initializeResources-Method from the WicketApplication.",
					e);
		}
	}
	
	public String getDomainName() {
		return "jaulp-wicket-components.com";
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
		prr.initializeResources(getPackageToScan());
	}

	/**
	 * Gets the WicketApplication.
	 * 
	 * @return the WicketApplication object.
	 */
	public static WicketApplication get() {
		return ((WicketApplication) Application.get());
	}

	public String getPackageToScan() {
		return "de.alpharogroup.wicket.components.examples";
	}
}
