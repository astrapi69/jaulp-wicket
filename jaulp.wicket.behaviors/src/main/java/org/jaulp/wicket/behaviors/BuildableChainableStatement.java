package org.jaulp.wicket.behaviors;

import java.io.Serializable;

import org.odlabs.wiquery.core.javascript.ChainableStatement;

public class BuildableChainableStatement implements ChainableStatement, Serializable
{

	private static final long serialVersionUID = 1L;

	/**
	 * The statement label.
	 */
	private String label;

	/**
	 * The statement args.
	 */
	private CharSequence[] args;

	@Override
	public String chainLabel()
	{
		return label;
	}

	@Override
	public CharSequence[] statementArgs()
	{
		return args;
	}

	public static class Builder
	{
		private String label;
		private CharSequence[] args;

		public Builder label(String label)
		{
			this.label = label;
			return this;
		}

		public Builder args(CharSequence... args)
		{
			this.args = args;
			return this;
		}

		public BuildableChainableStatement build()
		{
			return new BuildableChainableStatement(this);
		}
	}

	/**
	 * Creates a new instance of {@link BuildableChainableStatement}.
	 */
	private BuildableChainableStatement(Builder builder)
	{
		this.label = builder.label;
		this.args = builder.args;
	}
}
