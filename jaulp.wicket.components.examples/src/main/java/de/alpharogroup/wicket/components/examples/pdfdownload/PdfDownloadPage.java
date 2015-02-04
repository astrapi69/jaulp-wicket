package de.alpharogroup.wicket.components.examples.pdfdownload;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

import de.alpharogroup.wicket.components.download.DownloadModel;
import de.alpharogroup.wicket.components.download.DownloadPanel;
import de.alpharogroup.wicket.components.examples.application.WicketApplication;
import de.alpharogroup.wicket.components.examples.area.publicly.PubliclyBasePage;


@MountPath("public/download/pdf")
public class PdfDownloadPage extends PubliclyBasePage<Object>
{
	private static final long serialVersionUID = 1L;

	@Override
	public Component getContainerPanel()
	{
		DownloadModel downloadModel = DownloadModel.builder().filename("download.pdf")
			.path("pdf/download.pdf").contentType("application/pdf").build();
		return new DownloadPanel(CONTAINER_PANEL_ID, Model.of(downloadModel))
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected WebApplication getWebApplication()
			{
				return WicketApplication.get();
			}

		};
	}

	public PdfDownloadPage(final PageParameters parameters)
	{
		super(parameters);
	}

}
