package com.github.szysza26.pgservletgeojsonexample;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setProperty("org.geotools.referencing.forceXY", "true");
		String webappDirLocation = "src/main/webapp/";
		
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		
		Context context = tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
		
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(context);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
		context.setResources(resources);
		
		tomcat.start();
        tomcat.getServer().await();
	}

}