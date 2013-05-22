package org.jaulp.wicket.dropdownchoices.models;

public class Country {
    private String digraph;

    private String name;

    public Country( final String digraph, final String name ) {
        this.digraph = digraph;
        this.name = name;
    }

    public String getDigraph() {
        return digraph;
    }

    public String getName() {
        return name;
    }

    public void setDigraph( final String digraph ) {
        this.digraph = digraph;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    // ... getters and setters omitted.
}
