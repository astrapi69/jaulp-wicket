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
package de.alpharogroup.wicket.base.request.mapper;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.CompoundRequestMapper;
import org.apache.wicket.util.lang.Args;

/**
 * Compound {@link IRequestMapper} with request mappers, orders by the compatibility score they were
 * added.
 */
public class HighScoreRequestMapper extends CompoundRequestMapper
{

	private List<IRequestMapper> requestMappers = new CopyOnWriteArrayList<>();

	private Comparator<RequestMapperBean> comparator;

	public HighScoreRequestMapper()
	{
	}

	public HighScoreRequestMapper(final List<IRequestMapper> requestMappers)
	{
		this.requestMappers = Args.notNull(requestMappers, "requestMappers");
	}

	public HighScoreRequestMapper register(final IRequestMapper encoder)
	{
		this.requestMappers.add(0, encoder);
		return this;
	}

	public HighScoreRequestMapper unregister(final IRequestMapper encoder)
	{
		this.requestMappers.remove(encoder);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public IRequestHandler mapRequest(final Request request)
	{
		for (RequestMapperBean mapperBean : initializeRequestMappers(request))
		{
			final IRequestHandler handler = mapperBean.getMapper().mapRequest(request);
			if (handler != null)
			{
				return handler;
			}
		}
		return null;
	}

	private Collection<RequestMapperBean> initializeRequestMappers(final Request request)
	{
		final Set<RequestMapperBean> mapperBeans = new TreeSet<>(getComparator());
		for (IRequestMapper requestMapper : this.requestMappers)
		{
			mapperBeans.add(new RequestMapperBean(requestMapper, requestMapper
				.getCompatibilityScore(request)));
		}
		return mapperBeans;
	}

	/**
	 * {@inheritDoc}
	 */
	public Url mapHandler(final IRequestHandler handler)
	{
		for (IRequestMapper requestMapper : this.requestMappers)
		{
			Url url = requestMapper.mapHandler(handler);
			if (url != null)
			{
				return url;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getCompatibilityScore(Request request)
	{
		int score = Integer.MIN_VALUE;
		for (IRequestMapper requestMapper : this.requestMappers)
		{
			score = Math.max(score, requestMapper.getCompatibilityScore(request));
		}
		return score;
	}

	/**
	 * Factory method for creating a new Comparator for sort the compatibility score. This method is
	 * invoked in the method initializeRequestMappers and can be overridden so users can provide
	 * their own version of a Comparator.
	 *
	 * @return the new Comparator.
	 */
	protected Comparator<RequestMapperBean> newComparator()
	{
		return new Comparator<RequestMapperBean>()
		{
			public int compare(RequestMapperBean o1, RequestMapperBean o2)
			{
				return o1.getCompatibilityScore() - o2.getCompatibilityScore();
			}
		};
	}

	/**
	 * Gets the Comparator for this SortedProperties.
	 * 
	 * @return The Comparator.
	 */
	private Comparator<RequestMapperBean> getComparator()
	{
		if (this.comparator == null)
		{
			this.comparator = newComparator();
		}
		return this.comparator;
	}

}
