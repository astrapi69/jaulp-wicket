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
package de.alpharogroup.wicket.behaviors;

import java.io.Serializable;

import org.odlabs.wiquery.core.javascript.ChainableStatement;

/**
 * The Class {@link BuildableChainableStatement} is an implementation of the
 * {@link ChainableStatement} interface.
 */
public class BuildableChainableStatement implements ChainableStatement, Serializable
{

	/**
	 * The {@link Builder} class for the {@link BuildableChainableStatement}.
	 */
	public static class Builder
	{

		/** The label. */
		private String label;

		/** The args. */
		private CharSequence[] args;

		/**
		 * Args.
		 *
		 * @param args
		 *            the args
		 * @return the builder
		 */
		public Builder args(final CharSequence... args)
		{
			this.args = args;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the buildable chainable statement
		 */
		public BuildableChainableStatement build()
		{
			return new BuildableChainableStatement(this);
		}

		/**
		 * Label.
		 *
		 * @param label
		 *            the label
		 * @return the builder
		 */
		public Builder label(final String label)
		{
			this.label = label;
			return this;
		}
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The statement label.
	 */
	private final String label;

	/**
	 * The statement args.
	 */
	private final CharSequence[] args;

	/**
	 * Creates a new instance of {@link BuildableChainableStatement}.
	 *
	 * @param builder
	 *            the builder
	 */
	private BuildableChainableStatement(final Builder builder)
	{
		this.label = builder.label;
		this.args = builder.args;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String chainLabel()
	{
		return label;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CharSequence[] statementArgs()
	{
		return args;
	}
}
