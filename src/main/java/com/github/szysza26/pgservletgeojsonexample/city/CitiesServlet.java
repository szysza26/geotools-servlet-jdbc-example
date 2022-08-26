package com.github.szysza26.pgservletgeojsonexample.city;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/cities")
public class CitiesServlet extends HttpServlet {
	
	private CityService cityService;
	
	public CitiesServlet() {
		this.cityService = new CityServiceImpl(new CityDAOImpl());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String citiesJsonString = new Gson().toJson(this.cityService.getAllCities());
		out.print(citiesJsonString);
		out.flush();
	}

}
