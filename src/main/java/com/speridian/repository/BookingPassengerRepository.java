package com.speridian.repository;

import java.util.List;

import com.speridian.entity.Booking;
import com.speridian.entity.Passengers;

public interface BookingPassengerRepository {

	void addBooking(Booking booking);

	List<Booking> fetchAllBooking();

	void addPassenger(Passengers passenger);

	int fetchBookingScheduleId();

	List<Object[]> viewBooking( int id);

	List<Passengers> fetchAllPassengersByBookingId(int id);

}