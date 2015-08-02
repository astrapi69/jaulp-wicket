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
package de.alpharogroup.wicket.ajax.call.listeners;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.IAjaxCallListener;

/**
 * This ajax call listener disables the ajax Button if it is clicked. Add this listener to the
 * attributes. Example:
 * 
 * <code>
 * 		Button submit = new AjaxFallbackButton("submit",form) {

			
			public void onSubmit(final AjaxRequestTarget target, final Form form) {	
				// do something...
			}
			
			
		   protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
		      super.updateAjaxAttributes(attributes);
		      attributes.getAjaxCallListeners().add(new DisableAjaxButtonAjaxCallListener("Please wait..."));
		   }

		};
 * 
 * 
 * </code>
 */
public class DisableAjaxButtonAjaxCallListener implements IAjaxCallListener
{

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new disable compontent ajax call listener.
	 *
	 * @param value
	 *            the value
	 */
	public DisableAjaxButtonAjaxCallListener(final String value)
	{
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getAfterHandler(org.apache.wicket.Component
	 * )
	 */
	@Override
	public CharSequence getAfterHandler(final Component component)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getBeforeHandler(org.apache.wicket.Component
	 * )
	 */
	@Override
	public CharSequence getBeforeHandler(final Component component)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getBeforeSendHandler(org.apache.wicket
	 * .Component)
	 */
	@Override
	public CharSequence getBeforeSendHandler(final Component component)
	{
		return getJsScript(component);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getCompleteHandler(org.apache.wicket.
	 * Component)
	 */
	@Override
	public CharSequence getCompleteHandler(final Component component)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getFailureHandler(org.apache.wicket.Component
	 * )
	 */
	@Override
	public CharSequence getFailureHandler(final Component component)
	{
		return null;
	}

	private String getJsScript(final Component component)
	{
		final StringBuilder sb = new StringBuilder();
		if (this.value != null)
		{
			sb.append("component.value=\"").append(this.value).append("\";");
		}
		final String jsscript = "var component = document.getElementById(\""
			+ component.getMarkupId() + "\");" + "component.disabled=true;" + sb.toString();
		return jsscript;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getPrecondition(org.apache.wicket.Component
	 * )
	 */
	@Override
	public CharSequence getPrecondition(final Component component)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.ajax.attributes.IAjaxCallListener#getSuccessHandler(org.apache.wicket.Component
	 * )
	 */
	@Override
	public CharSequence getSuccessHandler(final Component component)
	{
		return null;
	}

}