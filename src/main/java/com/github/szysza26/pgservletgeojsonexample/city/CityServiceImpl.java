package com.github.szysza26.pgservletgeojsonexample.city;

import java.util.List;
import java.util.stream.Collectors;

public class CityServiceImpl implements CityService{
	
	private final CityDAO cityDAO;


	public CityServiceImpl(CityDAO cityDAO) {
		super();
		this.cityDAO = cityDAO;
	}

	@Override
	public List<CityDTO> getAllCities() {
		return cityDAO.getCities().stream().map(city -> {
			Double latitude = 0d;
			Double longitude = 0d;
			if(city.getLocation() != null) {
				latitude = city.getLocation().getY();
				longitude = city.getLocation().getX();
			}
			return new CityDTO(city.getName(), city.getPopulation(), latitude, longitude);
		}).collect(Collectors.toList());
	}

}
