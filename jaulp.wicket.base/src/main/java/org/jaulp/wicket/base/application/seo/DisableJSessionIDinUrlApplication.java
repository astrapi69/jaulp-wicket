package org.jaulp.wicket.base.application.seo;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.protocol.http.servlet.ServletWebResponse;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.jaulp.wicket.base.util.BotAgentInspector;


/**
 * The Class DisableJSessionIDinUrlApplication overrides the method
 * <code>{@link WebApplication#newWebResponse(WebRequest, HttpServletResponse)}</code>
 * that checks if a request comes from a robot and if this is the case it
 * removes the sessiod id from the response object. If you cant extends this
 * class just override the method
 * <code>{@link WebApplication#newWebResponse(WebRequest, HttpServletResponse)}</code>
 * into your Application class, this will have the same effect.
 */
public abstract class DisableJSessionIDinUrlApplication extends WebApplication {

	/**
	 * Disable sessionId in the url if it comes from a robot.
	 * 
	 * @param webRequest
	 *            the web request
	 * @param httpServletResponse
	 *            the http servlet response
	 * @return the web response
	 */
	protected WebResponse newWebResponse(final WebRequest webRequest,
			final HttpServletResponse httpServletResponse) {
		return new ServletWebResponse((ServletWebRequest) webRequest,
				httpServletResponse) {

			@Override
			public String encodeURL(CharSequence url) {
				return isRobot(webRequest) ? url.toString() : super
						.encodeURL(url);
			}

			@Override
			public String encodeRedirectURL(CharSequence url) {
				return isRobot(webRequest) ? url.toString() : super
						.encodeRedirectURL(url);
			}

			private boolean isRobot(WebRequest request) {
				final String agent = webRequest.getHeader("User-Agent");
				return BotAgentInspector.isAgent(agent);
			}
		};
	}
}
