package de.alpharogroup.wicket.components.examples.sign.up;

import java.util.Set;

import net.sourceforge.jaulp.auth.models.BaseUsernameSignUpModel;
import net.sourceforge.jaulp.io.annotations.ImportResource;
import net.sourceforge.jaulp.io.annotations.ImportResources;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.jaulp.wicket.PackageResourceReferenceWrapper;
import org.jaulp.wicket.PackageResourceReferences;
import org.jaulp.wicket.base.enums.ResourceReferenceType;

import de.alpharogroup.wicket.components.sign.up.SignupPanel;
@ImportResources(resources = {
		@ImportResource(resourceName = "../jquery-1.10.0.js", resourceType = "js", index = 1),
		@ImportResource(resourceName = "../jqueryBootstrapWrapper.js", resourceType = "js", index = 2)})
public class SignupPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	@Override
	protected void onConfigure() {
		super.onConfigure();
		BaseUsernameSignUpModel modelObject = new BaseUsernameSignUpModel();
		modelObject.setEmail("");
		IModel<BaseUsernameSignUpModel> model = new CompoundPropertyModel<BaseUsernameSignUpModel>(
				modelObject);
		setDefaultModel(model);
		Form<BaseUsernameSignUpModel> form = new Form<>("form", model);
		addOrReplace(form);
		form.addOrReplace(new SignupPanel("signupPanel", (IModel<BaseUsernameSignUpModel>) getDefaultModel()));
		// Create submit button for the form
		final Button submitButton = new Button("signupButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSignup();
			}

		};
		form.addOrReplace(submitButton);
	}
	

	protected void onSignup() {
		BaseUsernameSignUpModel model = (BaseUsernameSignUpModel) getDefaultModelObject();
		System.out.println(model);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
				.getInstance().getPackageResourceReference(
						SignupPage.class);
		if (null != headerContributors && !headerContributors.isEmpty()) {
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors) {
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.JS)) {
					JavaScriptResourceReference reference = new JavaScriptResourceReference(
							SignupPage.class, packageResourceReference
									.getPackageResourceReference().getName());
					if (!response.wasRendered(reference)) {
						JavaScriptReferenceHeaderItem headerItem = JavaScriptHeaderItem
								.forReference(reference);
						response.render(headerItem);
					}
				}
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.CSS)) {
					CssResourceReference reference = new CssResourceReference(
							SignupPage.class, packageResourceReference
									.getPackageResourceReference().getName());
					if (!response.wasRendered(reference)) {
						CssReferenceHeaderItem headerItem = CssHeaderItem
								.forReference(reference);
						response.render(headerItem);
					}
				}
			}
		}
	}

}
