package com.github.szysza26.pgservletgeojsonexample;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class AppProperties {
	private static AppProperties instance;
	private Properties properties;
	
	private AppProperties() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		properties = new Properties();
		try {
			properties.load(new FileInputStream(appConfigPath));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static AppProperties getInstance() {
		if(instance == null) {
			instance = new AppProperties();
		}
		return instance;
	}
	
	public String getProperty(String propertyName) {
		return properties.getProperty(propertyName, "");
	}
	
}
