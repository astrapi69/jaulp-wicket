package org.jaulp.wicket.base.application.jetty;

import java.io.File;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import org.apache.wicket.Application;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServletContextHandlerConfiguration
{
	private Class<? extends Application> applicationClass;
	private File webapp;
	private String contextPath;
	private int maxInactiveInterval;
	private String filterPath;
	@Singular
	private Map<String, String> initParameters;
	@Singular
	private Map<String, String> contextHandlerInitParameters;
}
