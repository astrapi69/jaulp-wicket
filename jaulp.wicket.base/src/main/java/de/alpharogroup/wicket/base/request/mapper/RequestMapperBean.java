package de.alpharogroup.wicket.base.request.mapper;

import java.io.Serializable;

import lombok.Getter;

import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.util.lang.Args;

/**
 * TODO Document
 */
public class RequestMapperBean implements Serializable
{
	@Getter
	private int compatibilityScore;
	@Getter
	private IRequestMapper mapper;

	public RequestMapperBean(final IRequestMapper mapper, final int compatibilityScore)
	{
		this.mapper = Args.notNull(mapper, "mapper");
		this.compatibilityScore = compatibilityScore;
	}
}
