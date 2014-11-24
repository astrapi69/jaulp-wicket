package org.jaulp.wicket.base.util;

import java.io.IOException;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.IRequestLogger;
import org.apache.wicket.protocol.http.RequestLogger;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.resource.IResourceStream;
import org.jaulp.wicket.base.util.resource.ByteArrayResourceStreamWriter;

/**
 * The Class ApplicationUtils.
 */
public final class ApplicationUtils {


	/**
	 * private constructor.
	 */
	private ApplicationUtils() {
	}

	/**
	 * Initializes the given WebApplication with the Spring framework.
	 * 
	 * @param application
	 *            the WebApplication
	 */
	public static void initializeSpring(WebApplication application) {
		application.getComponentInstantiationListeners().add(
				new SpringComponentInjector(application));
	}

	/**
	 * Gets the context path from the given WebApplication.
	 * 
	 * @param application
	 *            the WebApplication
	 * @return the context path
	 */
	public static String getContextPath(final WebApplication application) {
		String contextPath = application.getServletContext().getContextPath();
		if (null != contextPath && !contextPath.isEmpty()) {
			return contextPath;
		}
		return "";
	}

	/**
	 * Gets the real path corresponding to the given virtual path from the given
	 * WebApplication. This method gets decorated the method of the
	 * {@link javax.servlet.ServletContext#getRealPath(String)}.
	 * 
	 * @param application
	 *            the wicket application
	 * @param path
	 *            the virtual path to be translated to a real path
	 * @return the real path, or null if the translation cannot be performed
	 */

	public static String getRealPath(final WebApplication application,
			String path) {
		String realPath = application.getServletContext().getRealPath(path);
		if (null != realPath && !realPath.isEmpty()) {
			return realPath;
		}
		return "";
	}

	/**
	 * Gets the request logger from the given WebApplication.
	 * 
	 * @param webApplication
	 *            the web application
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger(WebApplication webApplication) {
		if (webApplication == null) {
			webApplication = (WebApplication) Application.get();
		}
		IRequestLogger requestLogger = webApplication.getRequestLogger();
		if (requestLogger == null) {
			requestLogger = new RequestLogger();
		}
		return requestLogger;
	}

	/**
	 * Gets the request logger of the current WebApplication.
	 * 
	 * @return the request logger
	 */
	public static IRequestLogger getRequestLogger() {
		return getRequestLogger(null);
	}
	
	/**
	 * Gets the resource stream from the given parameters.
	 *
	 * @param application the application
	 * @param path the path
	 * @param contentType the content type
	 * @return the resource stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static IResourceStream getResourceStream(final WebApplication application,
			final String path, final String contentType) throws IOException {
    	return new ByteArrayResourceStreamWriter() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getContentType() {
				return contentType;
			}
			@Override
			protected byte[] load() throws IOException {
				byte[] data = null;
				final String realPath = ApplicationUtils.getRealPath(application, path);		    	
				final File file = new File(realPath);				
				data = Files.readBytes(file);				
				return data;
			}    		
    	};
	}
	
	/**
	 * Gets the default jquery reference from the given application.
	 *
	 * @param application the application
	 * @return the default jquery reference
	 */
	public static ResourceReference getJQueryReference(final Application application) {
		return application.getJavaScriptLibrarySettings().getJQueryReference();
	}
	
	/**
	 * Gets the default jquery reference from the current application.
	 *
	 * @param application the application
	 * @return the default jquery reference
	 */
	public static ResourceReference getJQueryReference() {
		return getJQueryReference(Application.get());
	}
}
