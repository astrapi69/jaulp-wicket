/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.behaviors;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.resource.IResourceStream;

/**
 * This behavior enables us to initiate the download after the AJAX request has been completed.
 * 
 * Inspired from @see <a href=
 * "https://cwiki.apache.org/confluence/display/WICKET/AJAX+update+and+file+download+in+one+blow"
 * >https://cwiki.apache.org/confluence/display/WICKET/AJAX+update+and+file+
 * download+in+one+blow</a>.
 **/
public abstract class AjaxDownloadBehavior extends AbstractAjaxBehavior
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The anti cache. */
	private boolean antiCache;

	/**
	 * Instantiates a new {@link AjaxDownloadBehavior}.
	 */
	public AjaxDownloadBehavior()
	{
		this(true);
	}

	/**
	 * Instantiates a new {@link AjaxDownloadBehavior}.
	 *
	 * @param antiCache
	 *            the anti cache
	 */
	public AjaxDownloadBehavior(final boolean antiCache)
	{
		this.antiCache = antiCache;
	}

	/**
	 * Hook method for a file name which will let the browser prompt with a save/open dialog.
	 * 
	 * @return the file name
	 */
	protected abstract String getFileName();

	/**
	 * Hook method providing the actual resource stream.
	 * 
	 * @return the resource stream.
	 */
	protected abstract IResourceStream getResourceStream();

	/**
	 * Call this method to initiate the download.
	 * 
	 * @param target
	 *            the {@link AjaxRequestTarget}
	 */
	public void initiate(final AjaxRequestTarget target)
	{
		String url = getCallbackUrl().toString();

		if (antiCache)
		{
			url = url + (url.contains("?") ? "&" : "?");
			url = url + "antiCache=" + System.currentTimeMillis();
		}
		// the timeout is needed to let Wicket release the channel
		target.appendJavaScript("setTimeout(\"window.location.href='" + url + "'\", 100);");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRequest()
	{
		final ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(
			getResourceStream(), getFileName());
		handler.setContentDisposition(ContentDisposition.ATTACHMENT);
		getComponent().getRequestCycle().scheduleRequestHandlerAfterCurrent(handler);
	}
}