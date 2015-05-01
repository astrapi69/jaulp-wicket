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
package de.alpharogroup.wicket.dropdownchoices.models;

public class Country
{
	private String digraph;

	private String name;

	public Country(final String digraph, final String name)
	{
		this.digraph = digraph;
		this.name = name;
	}

	public String getDigraph()
	{
		return digraph;
	}

	public String getName()
	{
		return name;
	}

	public void setDigraph(final String digraph)
	{
		this.digraph = digraph;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	// ... getters and setters omitted.
}
