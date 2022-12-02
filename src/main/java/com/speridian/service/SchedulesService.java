package com.speridian.service;

import java.util.List;

import com.speridian.dto.SearchFlightDto;
import com.speridian.entity.FlightSchedule;
import com.speridian.entity.Flights;
import com.speridian.entity.Routes;
import com.speridian.entity.Schedule;

public interface SchedulesService {

	void addFlight(Schedule schedule, FlightSchedule flightSchedule);

	Flights isAddFlightPossible(int fid);

	Routes getRoute(String fromCity, String toCity);

	void deleteFlight(int id);

	List<FlightSchedule> isDeleteFlightPossible(int fid);

	List<Object[]> fetchFlightSchedules(SearchFlightDto sfdto, int bs, int es);

}