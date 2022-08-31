# geotools-servlet-jdbc-example

Simple project with using some dependencies:
- geotools
- embedded tomcat
- servlet
- jdbc and postgis
- gson

Two endpoints who return same (return list of cities with location and information about name and population in json format), but using other technology:
- /cities (using jdbc with DAO pattern for fetching features from db)
- /gtcities (using FeatureSource from geotools for fetching features from db)
