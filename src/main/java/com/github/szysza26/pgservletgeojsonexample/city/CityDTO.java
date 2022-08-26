package com.github.szysza26.pgservletgeojsonexample.city;

public class CityDTO {
	private String name;
	private Integer population;
	private Double latitude;
	private Double longitude;

	public CityDTO(String name, Integer population, Double latitude, Double longitude) {
		this.name = name;
		this.population = population;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public Integer getPopulation() {
		return population;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
}
