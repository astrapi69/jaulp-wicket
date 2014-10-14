package de.alpharogroup.wicket.components.examples.application;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.JavaScriptFilteredIntoFooterHeaderResponse;
import org.apache.wicket.markup.html.IHeaderResponseDecorator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.jaulp.wicket.PackageResourceReferences;

import de.alpharogroup.wicket.bootstrap2.application.WicketBootstrapApplication;
import de.alpharogroup.wicket.components.examples.home.HomePage;


/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see de.alpharogroup.wicket.components.examples.StartComponentExamples#main(String[])
 */
public class WicketApplication extends WicketBootstrapApplication
{    	

	/** The Constant logger. */
	private static final Logger LOGGER = Logger
			.getLogger(WicketApplication.class.getName());

	// http://www.wicket-library.com/wicket-examples/resourceaggregation/wicket/bookmarkable/org.apache.wicket.examples.source.SourcesPage?0&SourcesPage_class=org.apache.wicket.examples.resourcedecoration.HomePage&source=HomePage.java
	public static final String FOOTER_FILTER_NAME = "footer-container";
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
		// add your configuration here
		initializeAllHeaderContributors();
		// set footer scripts...
		setHeaderResponseDecorator(new IHeaderResponseDecorator() {
			public IHeaderResponse decorate(IHeaderResponse response) {
				return new JavaScriptFilteredIntoFooterHeaderResponse(response,
						FOOTER_FILTER_NAME);
			}
		});
		// set up ports for http and https...
		setRootRequestMapper(new HttpsMapper(getRootRequestMapper(),
				new HttpsConfig(8080, 8443)));	
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
