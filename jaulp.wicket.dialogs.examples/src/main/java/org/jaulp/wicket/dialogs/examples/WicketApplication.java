/**
 * Copyright (C) 2007 Asterios Raptis
 *
 * This program is open source software; you can redistribute it and/or modify
 * it under the terms of the Apache License V2.0 as published by
 * the Apache Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY.
 */
package org.jaulp.wicket.dialogs.examples;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.file.Folder;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see StartDialogsExamples.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{

	/** The upload folder. */
	private Folder uploadFolder = null;
    /**
     * Constructor.
     */
	public WicketApplication()
	{


	}

	public void init() {
        // Create the upload folder...
        uploadFolder = new Folder(System.getProperty("java.io.tmpdir"), "wicket-uploads");
        // Ensure folder exists
        uploadFolder.mkdirs();
	}

    /**
     * Gets the home page.
     *
     * @return the home page
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class< HomePage > getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @return the folder for uploads
	 */
	public Folder getUploadFolder() {
		return this.uploadFolder;
	}

	/**
	 * Gets the.
	 *
	 * @return the wicket application
	 */
	public static WicketApplication get() {
		return ((WicketApplication) Application.get());
	}

}
