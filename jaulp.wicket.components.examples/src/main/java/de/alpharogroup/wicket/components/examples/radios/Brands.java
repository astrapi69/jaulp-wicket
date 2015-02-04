package de.alpharogroup.wicket.components.examples.radios;

import lombok.Getter;

public enum Brands
{

	LAMBORGINI("Lamborgini"), MASERATI("Maserati"), FERRARI("Ferrari"), PORSCHE("Porsche");
	@Getter
	private String value;

	Brands(String value)
	{
		this.value = value;
	}

}