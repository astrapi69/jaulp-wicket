package de.alpharogroup.wicket.components.examples.sign.in;

import java.util.Set;

import net.sourceforge.jaulp.auth.models.BaseSignInModel;
import net.sourceforge.jaulp.auth.models.SignInModel;
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

import de.alpharogroup.wicket.components.sign.in.SigninPanel;
import de.alpharogroup.wicket.components.sign.in.form.SinginFormPanel;
@ImportResources(resources = {
		@ImportResource(resourceName = "../jquery-1.10.0.js", resourceType = "js", index = 1),
		@ImportResource(resourceName = "../jqueryBootstrapWrapper.js", resourceType = "js", index = 2)})
public class SigninPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		final SignInModel model = new BaseSignInModel();

		final IModel<SignInModel> cpm = new CompoundPropertyModel<SignInModel>(
				model);
		SinginFormPanel signFormPanel = new SinginFormPanel("signFormPanel", cpm) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSignin() {
				System.out.println(model.getEmail());
				System.out.println(model.getPassword());
				
			}
			
//			@Override
//			protected Label newButtonLabel(String id, String resourceKey,
//					String defaultValue, Component component) {
//				// TODO Auto-generated method stub
//				return new Label(id, "blafasel");
//			}
		};
		add(signFormPanel);
		Form<SignInModel> form = new Form<SignInModel>("form", cpm);

		add(form);
		
		form.add(new SigninPanel("signinPanel", cpm)
		);
		// Create submit button for the form
		final Button submitButton = new Button("signinButton") {
			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				onSignin();
				System.out.println(model.getEmail());
			}

		};
		form.add(submitButton);
	}

	protected void onSignin() {
		// TODO Auto-generated method stub
		
	}	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		Set<PackageResourceReferenceWrapper> headerContributors = PackageResourceReferences
				.getInstance().getPackageResourceReference(
						SigninPage.class);
		if (null != headerContributors && !headerContributors.isEmpty()) {
			for (final PackageResourceReferenceWrapper packageResourceReference : headerContributors) {
				if (packageResourceReference.getType().equals(
						ResourceReferenceType.JS)) {
					JavaScriptResourceReference reference = new JavaScriptResourceReference(
							SigninPage.class, packageResourceReference
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
							SigninPage.class, packageResourceReference
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
