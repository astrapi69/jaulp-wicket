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
package de.alpharogroup.wicket.data.provider.examples.data.provider;

import java.io.Serializable;
import java.util.Date;

public class PersonFilter implements Serializable
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;

	private Date dateFrom;
	private Date dateTo;

	public PersonFilter()
	{
		super();
	}

	public Date getDateFrom()
	{
		return dateFrom;
	}

	public Date getDateTo()
	{
		return dateTo;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setDateFrom(final Date dateFrom)
	{
		this.dateFrom = dateFrom;
	}

	public void setDateTo(final Date dateTo)
	{
		this.dateTo = dateTo;
	}

	public void setFirstname(final String firstname)
	{
		this.firstname = firstname;
	}

	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}


}
