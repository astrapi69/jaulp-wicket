package de.alpharogroup.wicket.components.examples.exceptions;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;
import de.alpharogroup.wicket.components.examples.home.HomePage;
import de.alpharogroup.wicket.components.report.ReportThrowablePanel;

@MountPath("public/exception")
public class ExceptionPage extends PubliclyBasePage<Exception> {
	private static final long serialVersionUID = 1L;
	private final Exception exception;
	public Exception getException() {
		return exception;
	}

	public ExceptionPage(final Exception exception) {
		super(Model.of(exception));
		setModelObject(exception);
		this.exception = exception;
	}

	public ExceptionPage() {
		this(new IllegalArgumentException("exception example..."));
	}

	@Override
	public Panel getContainerPanel() {

		return new ReportThrowablePanel(CONTAINER_PANEL_ID, getModelObject()){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmitError() {
				// the description of the user...
//				Object model =  getDefaultModelObject();
				// the real exception...
//				Exception exception = PubliclyExcptionPage.this.getModelObject();
				// TODO send an email with the descripton...
				setResponsePage(HomePage.class);
			}			

			@Override
			protected String newRootUsername() {
				return "rootUser";
			}

			@Override
			protected Class<? extends IRequestablePage> newResponsePageClass() {
				return HomePage.class;
			}

			@Override
			protected String newAffectedUsername() {				
				return "Guest";
			}
					
		};
	}
}