package de.alpharogroup.wicket.behaviors.popupoverlay;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * The Class PopupoverlayResourceReference holds the references(js) for the jquery plugin
 * jquery-popup-overlay.
 */
public class PopupoverlayResourceReference extends JavaScriptResourceReference
{

  /** The serialVersionUID. */
  private static final long serialVersionUID = 1L;

  public static final PopupoverlayResourceReference INSTANCE = new PopupoverlayResourceReference();

  private PopupoverlayResourceReference() {
    super(PopupoverlayResourceReference.class, "jquery.popupoverlay.js");
  }
}
