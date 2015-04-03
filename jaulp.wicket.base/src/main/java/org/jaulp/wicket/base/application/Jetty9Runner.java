package org.jaulp.wicket.base.application;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.EnumSet;

import javax.management.MBeanServer;
import javax.servlet.DispatcherType;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

import net.sourceforge.jaulp.file.search.PathFinder;

import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.PropertiesConfigurationManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;

public class Jetty9Runner {

  public static void run(Class<? extends Application> applicationClass,
                         File webapp) {
    run(applicationClass, webapp, 8080, 8443);
  }

  public static void run(Class<? extends Application> applicationClass,
                         File webapp, int httpPort, int httpsPort) {
    run(getServletContextHandler(applicationClass, webapp), httpPort, httpsPort);
  }

  public static void run(ServletContextHandler servletContextHandler, int httpPort, int httpsPort) {
    Server server = new Server();
    run(server, servletContextHandler, httpPort, httpsPort);
  }

  private static void run(Server server, ServletContextHandler servletContextHandler, int httpPort, int httpsPort) {
    HttpConfiguration http_config = new HttpConfiguration();
    http_config.setSecureScheme("https");
    http_config.setSecurePort(httpsPort);
    http_config.setOutputBufferSize(32768);

    ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
    http.setPort(httpPort);
    http.setIdleTimeout(1000 * 60 * 60);

    server.addConnector(http);

    Resource keystore = Resource.newClassPathResource("/keystore");
    if (keystore != null && keystore.exists())
    {
      // if a keystore for a SSL certificate is available, start a SSL
      // connector on port 'httpsPort'.
      // By default, the quickstart comes with a Apache Wicket Quickstart
      // Certificate that expires about half way september 2021. Do not
      // use this certificate anywhere important as the passwords are
      // available in the source.

      SslContextFactory sslContextFactory = new SslContextFactory();
      sslContextFactory.setKeyStoreResource(keystore);
      sslContextFactory.setKeyStorePassword("wicket");
      sslContextFactory.setKeyManagerPassword("wicket");

      HttpConfiguration https_config = new HttpConfiguration(http_config);
      https_config.addCustomizer(new SecureRequestCustomizer());

      ServerConnector https = new ServerConnector(server, new SslConnectionFactory(
        sslContextFactory, "http/1.1"), new HttpConnectionFactory(https_config));
      https.setPort(httpsPort);
      https.setIdleTimeout(500000);

      server.addConnector(https);
      System.out.println("SSL access to the examples has been enabled on port " + httpsPort);
      System.out
        .println("You can access the application using SSL on https://localhost:" + httpsPort);
      System.out.println();
    }

    server.setHandler(servletContextHandler);

    MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
    server.addEventListener(mBeanContainer);
    server.addBean(mBeanContainer);

    try
    {
      server.start();
      server.join();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.exit(100);
    }
  }

  public static ServletContextHandler getServletContextHandler(
    Class<? extends Application> applicationClass,
    String contextPath,
    File webapp,
    int maxInactiveInterval,
    String filterPath) {
    final ServletContextHandler context = new ServletContextHandler(
      ServletContextHandler.SESSIONS);
    context.setContextPath(contextPath);

    context.setResourceBase(webapp.getAbsolutePath());

    final FilterHolder filter = new FilterHolder(WicketFilter.class);
    filter.setInitParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
      applicationClass.getName());
    filter.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath);
    context.addFilter(filter, filterPath,
      EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR));
    context.addServlet(DefaultServlet.class, filterPath);

    context.getSessionHandler().getSessionManager().setMaxInactiveInterval(maxInactiveInterval);
    return context;
  }

  public static ServletContextHandler getServletContextHandler(
    Class<? extends Application> applicationClass,
    File webapp) {
    return getServletContextHandler(applicationClass, "/", webapp, 300, "/*");
  }

  public static ServletContextHandler getServletContextHandler(
    Class<? extends Application> applicationClass) {
    return getServletContextHandler(applicationClass, "/", PathFinder.getSrcMainJavaDir(), 300, "/*");
  }

  public static WebAppContext getWebAppContext(Server server, String projectname) {
    File webapp = PathFinder.getProjectDirectory();
    File wa = PathFinder.getRelativePath(webapp, projectname, "src", "main", "webapp");
    WebAppContext webAppContext = new WebAppContext();
    webAppContext.setServer(server);
    webAppContext.setContextPath("/");
    webAppContext.setWar(wa.getAbsolutePath());
    return webAppContext;
  }
}
