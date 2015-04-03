package org.jaulp.wicket.base.application.jetty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.eclipse.jetty.servlet.ServletContextHandler;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jetty9RunConfiguration
{
	
	ServletContextHandler servletContextHandler;
	int httpPort;
	int httpsPort;
	String keyStorePathResource;
	String keyStorePassword;
}
