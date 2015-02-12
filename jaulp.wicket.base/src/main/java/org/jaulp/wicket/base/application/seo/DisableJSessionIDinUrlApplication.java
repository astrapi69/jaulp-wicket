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
package org.jaulp.wicket.base.application.seo;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebResponse;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.jaulp.wicket.base.application.BaseWebApplication;
import org.jaulp.wicket.base.util.seo.BotAgentInspector;


/**
 * The Class DisableJSessionIDinUrlApplication overrides the method
 * <code>{@link WebApplication#newWebResponse(WebRequest, HttpServletResponse)}</code> that checks
 * if a request comes from a robot and if this is the case it removes the sessiod id from the
 * response object. If you cant extends this class just override the method
 * <code>{@link WebApplication#newWebResponse(WebRequest, HttpServletResponse)}</code> into your
 * Application class, this will have the same effect.
 */
public abstract class DisableJSessionIDinUrlApplication extends BaseWebApplication
{

	/**
	 * Disable sessionId in the url if it comes from a robot.
	 * 
	 * @param webRequest
	 *            the web request
	 * @param httpServletResponse
	 *            the http servlet response
	 * @return the web response
	 */
	@Override
	protected WebResponse newWebResponse(final WebRequest webRequest,
		final HttpServletResponse httpServletResponse)
	{
		return new ServletWebResponse((ServletWebRequest)webRequest, httpServletResponse)
		{

			@Override
			public String encodeURL(CharSequence url)
			{
				return isRobot(webRequest) ? url.toString() : super.encodeURL(url);
			}

			@Override
			public String encodeRedirectURL(CharSequence url)
			{
				return isRobot(webRequest) ? url.toString() : super.encodeRedirectURL(url);
			}

			private boolean isRobot(WebRequest request)
			{
				final String agent = webRequest.getHeader("User-Agent");
				return BotAgentInspector.isAgent(agent);
			}
		};
	}
}
