package com.github.szysza26.pgservletgeojsonexample.city;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import com.github.szysza26.pgservletgeojsonexample.AppProperties;

public class CityDAOImpl implements CityDAO {
	
	public List<City> getCities() {
		List<City> cities = new ArrayList<City>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			AppProperties appProperties = AppProperties.getInstance();
			conn = DriverManager.getConnection(
					appProperties.getProperty("dburl"), 
					appProperties.getProperty("dbuser"), 
					appProperties.getProperty("dbpassword"));
			stmt = conn.createStatement();
			res = stmt.executeQuery(
					"SELECT name, population, ST_AsText(ST_Transform(geom, 4326)) as geom from cities");

			while (res.next()) {
				WKTReader wktReader = new WKTReader();
				Point location = null;
				try {
					location = (Point) wktReader.read(res.getString("geom"));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				City city = new City(res.getString("name"), res.getInt("population"), location);

				cities.add(city);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (res != null)
					res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cities;
	}
	
}
