package com.speridian.repository;

import java.util.List;

import com.speridian.entity.Booking;
import com.speridian.entity.FlightSchedule;
import com.speridian.entity.Passengers;
import com.speridian.entity.Seats;

public interface CancelBookingRepository {

	Booking fetchBookingById(int id);

	List<Seats> fetchBookkedSeatsByBookedId(int id);

	FlightSchedule fetchFlightSchedule(int id);

	List<Passengers> fetchAllPassengersByBookingId(int id);

}