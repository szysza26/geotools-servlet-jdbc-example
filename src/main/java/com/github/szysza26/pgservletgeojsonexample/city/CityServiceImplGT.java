package com.github.szysza26.pgservletgeojsonexample.city;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Point;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;


public class CityServiceImplGT implements CityService {
	
	private final DataStore cityStory;
	private final SimpleFeatureSource citySource;
	private final MathTransform transform;
	
	public CityServiceImplGT() throws IOException, NoSuchAuthorityCodeException, FactoryException {
		Map<String, Object> params = new HashMap<>();
        params.put("dbtype", "postgis");
        params.put("host", "localhost");
        params.put("port", 5432);
        params.put("schema", "public");
        params.put("database", "db");
        params.put("user", "postgis");
        params.put("passwd", "postgis");

        cityStory = DataStoreFinder.getDataStore(params);
        
        if(cityStory == null) throw new IOException();
        
        citySource = cityStory.getFeatureSource("cities");
        
        transform = CRS.findMathTransform(CRS.decode("EPSG:2180", true), CRS.decode("EPSG:4326", true), false);
	}

	@Override
	public List<CityDTO> getAllCities() {
		List<CityDTO> cities = new ArrayList<>();
		
		SimpleFeatureIterator cityIterator;
		try {
			cityIterator = citySource.getFeatures().features();
			while(cityIterator.hasNext()) {
				SimpleFeature feature = cityIterator.next();
				Point point_2180 = (Point) feature.getDefaultGeometry();
				Point point_4326 = (Point) JTS.transform(point_2180, transform);
				
				CityDTO city = new CityDTO(
									(String) feature.getAttribute("name"),
									(Integer) feature.getAttribute("population"),
									point_4326.getY(),
									point_4326.getX()
								);
				
				cities.add(city);
			}
		} catch (IOException | MismatchedDimensionException | TransformException e) {
			e.printStackTrace();
		}
		
		return cities;
	}

}
