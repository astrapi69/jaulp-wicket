package org.jaulp.wicket.base.application.jetty;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.Servlet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServletHolderConfiguration implements Serializable
{

	/**
	 * The serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Class<? extends Servlet> servletClass;
	@Singular
	private Map<String, String> initParameters;

	private String pathSpec;

	private String name;


}
