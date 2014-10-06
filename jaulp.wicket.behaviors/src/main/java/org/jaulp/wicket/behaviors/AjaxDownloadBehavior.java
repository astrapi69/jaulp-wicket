package org.jaulp.wicket.behaviors;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.resource.IResourceStream;

/**
 * This behavior enables us to initiate the download after the AJAX request has
 * been completed.
 * 
 * Inspired from @see <a href=
 * "https://cwiki.apache.org/confluence/display/WICKET/AJAX+update+and+file+download+in+one+blow"
 * >https://cwiki.apache.org/confluence/display/WICKET/AJAX+update+and+file+
 * download+in+one+blow</a>.
 **/
public abstract class AjaxDownloadBehavior extends AbstractAjaxBehavior {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean antiCache;

	public AjaxDownloadBehavior() {
		this(true);
	}

	public AjaxDownloadBehavior(boolean antiCache) {
		super();
		this.antiCache = antiCache;
	}

	/**
	 * Call this method to initiate the download.
	 */
	public void initiate(AjaxRequestTarget target) {
		String url = getCallbackUrl().toString();

		if (antiCache) {
			url = url + (url.contains("?") ? "&" : "?");
			url = url + "antiCache=" + System.currentTimeMillis();
		}
		// the timeout is needed to let Wicket release the channel
		target.appendJavaScript("setTimeout(\"window.location.href='" + url
				+ "'\", 100);");
	}

	public void onRequest() {
		ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(
				getResourceStream(), getFileName());
		handler.setContentDisposition(ContentDisposition.ATTACHMENT);
		getComponent().getRequestCycle().scheduleRequestHandlerAfterCurrent(
				handler);
	}

	/**
	 * Hook method for a file name which will let the browser prompt with a
	 * save/open dialog.
	 * 
	 * @see ResourceStreamRequestTarget#getFileName()
	 */
	protected abstract String getFileName();

	/**
	 * Hook method providing the actual resource stream.
	 */
	protected abstract IResourceStream getResourceStream();
}