package de.alpharogroup.wicket.behaviors.popupoverlay;

import org.apache.wicket.util.lang.Args;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class PopupoverlayJsGenerator generates the javascript with a PopupoverlaySettings object.
 */
public class PopupoverlayJsGenerator extends JavascriptGenerator<PopupoverlaySettings>
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new {@link PopupoverlayJsGenerator}.
   *
   * @param componentId
   *            the component id
   */
  public PopupoverlayJsGenerator(final String componentId)
  {
    this(new PopupoverlaySettings(), componentId);
  }

  /**
   * Instantiates a new {@link PopupoverlayJsGenerator} with the given {@link PopupoverlaySettings}.
   *
   * @param settings
   *            the settings for the jquery-popup-overlay plugin.
   *
   * @param componentId
   *            the component id
   */
  public PopupoverlayJsGenerator(PopupoverlaySettings settings, final String componentId)
  {
    super(settings);
    setComponentId(Args.notEmpty(componentId, "componentId"));
    setWithComponentId(true);
    setMethodName("popup");
  }

}
