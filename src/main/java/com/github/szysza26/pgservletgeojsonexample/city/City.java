package com.github.szysza26.pgservletgeojsonexample.city;

import org.locationtech.jts.geom.Point;

public class City {
	private String name;
	private Integer population;
	private Point location;

	public City(String name, Integer population, Point location) {
		this.name = name;
		this.population = population;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public Integer getPopulation() {
		return population;
	}

	public Point getLocation() {
		return location;
	}
}
