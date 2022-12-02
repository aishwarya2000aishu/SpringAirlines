package com.speridian.repository;

import java.util.List;

import com.speridian.dto.SearchFlightDto;
import com.speridian.entity.FlightSchedule;
import com.speridian.entity.Schedule;

public interface SchedulesRepository {

	void addSchedule(Schedule schedule);

	void addFlightSchedule(FlightSchedule flightSchedule);

	List<Schedule> fetchAllSchedules();

	List<FlightSchedule> fetchAllFlightSchedules();

	int fetchScheduleId();

	List<FlightSchedule> fetchFlightScheduleById(int id);

	int fetchFlightScheduleId();

	List<Object[]> fetchSearchedFlights(SearchFlightDto sfdto, int bs, int es);

	FlightSchedule fetchSingleFlightScheduleById(int id);

}