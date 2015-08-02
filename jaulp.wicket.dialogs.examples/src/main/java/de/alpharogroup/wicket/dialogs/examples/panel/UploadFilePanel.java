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
package de.alpharogroup.wicket.dialogs.examples.panel;

import java.io.File;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;

import de.alpharogroup.wicket.dialogs.examples.WicketApplication;

/**
 * @author asterios
 */
public class UploadFilePanel extends Panel
{

	/**
	 * Form for uploads.
	 */
	private class FileUploadForm extends Form<Void>
	{
		/**
		 * The serialVersionUID.
		 */
		private static final long serialVersionUID = 1L;

		private FileUploadField fileUploadField;

		/**
		 * Construct.
		 *
		 * @param name
		 *            Component name
		 */
		public FileUploadForm(final String name)
		{
			super(name);
			// set this form to multipart mode (allways needed for uploads!)
			setMultiPart(true);

			final Label lblAddLogo = new Label("lblAddLogo", "Upload File");
			add(lblAddLogo);

			final Label lblFile = new Label("lblFile", "File:");
			add(lblFile);

			// Add one file input field
			fileUploadField = new FileUploadField("fileInput");
			add(getFileUploadField());

			// Set maximum size to 500K for demo purposes
			setMaxSize(Bytes.kilobytes(500));
		}

		public FileUploadField getFileUploadField()
		{
			return fileUploadField;
		}

		/**
		 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
		 */
		@Override
		protected void onSubmit()
		{
			final FileUpload upload = fileUploadField.getFileUpload();
			if (upload != null)
			{
				// Create a new file
				final File newFile = new File(getUploadFolder(), upload.getClientFileName());

				// Check new file, delete if it allready existed
				checkFileExists(newFile);

				try
				{
					// Save to new file
					newFile.createNewFile();
					upload.writeTo(newFile);

				}
				catch (final Exception e)
				{
					throw new IllegalStateException("Unable to write file");
				}
				onUploadFile();

			}
		}
	}

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public UploadFilePanel(final String id)
	{
		super(id);
		// Add upload form with ajax progress bar
		final FileUploadForm simpleUploadForm = new FileUploadForm("ajaxUpload");
		add(simpleUploadForm);
	}

	/**
	 * Check whether the file allready exists, and if so, try to delete it.
	 *
	 * @param newFile
	 *            the file to check
	 */
	private void checkFileExists(final File newFile)
	{
		if (newFile.exists())
		{
			// Try to delete the file
			if (!Files.remove(newFile))
			{
				throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
			}
		}
	}

	private Folder getUploadFolder()
	{
		return WicketApplication.get().getUploadFolder();
	}

	public void onUploadFile()
	{
	}

}
