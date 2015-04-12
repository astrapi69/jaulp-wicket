package org.jaulp.wicket.base.application.jetty;

import java.io.File;
import java.util.List;
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
	private List<FilterHolderConfiguration> filterHolderConfigurations;
	@Singular
	private List<ServletHolderConfiguration> servletHolderConfigurations;
	@Singular
	private Map<String, String> initParameters;
	/**
	 * use instead initParameters.
	 * */
	@Singular @Deprecated
	private Map<String, String> contextHandlerInitParameters;
}
