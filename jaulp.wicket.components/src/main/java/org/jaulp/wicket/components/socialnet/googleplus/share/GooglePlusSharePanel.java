package org.jaulp.wicket.components.socialnet.googleplus.share;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.jaulp.wicket.base.BasePanel;

public class GooglePlusSharePanel extends BasePanel<GooglePlusShareModel> {

	private static final long serialVersionUID = 1L;
	
	private Label googleScriptLabel;
	
	private WebMarkupContainer googlePlusButton;

	public GooglePlusSharePanel(String id) {
		this(id, null);
	}

	public GooglePlusSharePanel(String id, IModel<GooglePlusShareModel> model) {
		super(id, model);
		add(googleScriptLabel = newLabel("googleScriptLabel", model));
		add(googlePlusButton = newWebMarkupContainer("googlePlusButton", model));
	}
	
	protected WebMarkupContainer newWebMarkupContainer(String id, IModel<GooglePlusShareModel> model) {
		WebMarkupContainer googlePlusButton = new WebMarkupContainer(id);
		googlePlusButton.add(new AttributeModifier("class", model.getObject().getCssClass()));
		googlePlusButton.add(new AttributeModifier("data-annotation", model.getObject().getDataAnnotation()));
		googlePlusButton.add(new AttributeModifier("data-width", model.getObject().getDataWith()));
		googlePlusButton.add(new AttributeModifier("data-href", model.getObject().getDataHref()));
		return googlePlusButton;
	}
	
	protected Label newLabel(String id, IModel<GooglePlusShareModel> model) {
		Label googleScriptLabel = new Label(id, Model.of("{lang: '"+ model.getObject().getLocale()+"'}"));
		googleScriptLabel.add(new AttributeModifier("src", model.getObject().getScriptSrc()));
		return googleScriptLabel;
	}

	public Label getGoogleScriptLabel() {
		return googleScriptLabel;
	}

	public WebMarkupContainer getGooglePlusButton() {
		return googlePlusButton;
	}

}
