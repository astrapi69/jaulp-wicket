package de.alpharogroup.wicket.behaviors.popupoverlay;

import de.alpharogroup.wicket.base.util.template.JavascriptGenerator;

/**
 * The Class PopupoverlayJsGenerator generates the javascript with a PopupoverlaySettings object.
 */
public class PopupoverlayJsGenerator extends JavascriptGenerator<PopupoverlaySettings>
{

	{
		/**
		 * The jQuery Popup Overlay plugin needs a selector to build the javascript code.
		 */
		setWithComponentId(true);
	}
	
	public PopupoverlayJsGenerator(PopupoverlaySettings popupoverlaySettings)
	{
		super(popupoverlaySettings);
	}

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String... args)
	{
		PopupoverlaySettings popupoverlaySettings = new PopupoverlaySettings();
		popupoverlaySettings.getEscape().setValue(false);
		popupoverlaySettings.getFocusdelay().setValue(400);
		popupoverlaySettings.getHorizontal().setValue(HorizontalPosition.LEFT);
		PopupoverlayJsGenerator generator = new PopupoverlayJsGenerator(popupoverlaySettings);
		generator.setComponentId("aComponent");
		String result = generator.generatePopupoverlayJs(popupoverlaySettings);

		System.out.println(result);
	}

	public String generatePopupoverlayJs(PopupoverlaySettings settings)
	{
		return generateJs(settings, "popup");
	}
}
