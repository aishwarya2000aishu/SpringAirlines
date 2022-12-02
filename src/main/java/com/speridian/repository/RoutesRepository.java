package com.speridian.repository;


import java.util.List;

import com.speridian.entity.Routes;

public interface RoutesRepository {

	Routes fetchRouteById(int id);
	
	List<Routes> fetchAllRoutes();

	Routes fetchRoute(String fromCity, String toCity);

}