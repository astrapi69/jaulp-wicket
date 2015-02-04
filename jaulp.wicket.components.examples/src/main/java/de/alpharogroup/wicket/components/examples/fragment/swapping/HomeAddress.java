package de.alpharogroup.wicket.components.examples.fragment.swapping;

import java.io.Serializable;

public class HomeAddress implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String street;
	private String localNumber;
	private String city;
	private String code;

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getLocalNumber()
	{
		return localNumber;
	}

	public void setLocalNumber(String localNumber)
	{
		this.localNumber = localNumber;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
