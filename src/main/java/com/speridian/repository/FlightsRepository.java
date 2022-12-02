package com.speridian.repository;

import java.util.List;

import com.speridian.entity.Flights;
import com.speridian.entity.Routes;

public interface FlightsRepository {

	Flights fetchFlightById(int id);
	
	List<Flights> fetchAllFlights();

	boolean isFlight(int id);
	
}