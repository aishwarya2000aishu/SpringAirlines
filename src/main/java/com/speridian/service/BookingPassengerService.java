package com.speridian.service;

import java.util.List;

import com.speridian.dto.AddBookingDto;
import com.speridian.dto.GenerateTicketDto;

public interface BookingPassengerService {

	void addBookingAndPassengers(AddBookingDto bookingDto, int id);

	List<Object[]> getAllPassengers(int id);

	GenerateTicketDto fetchGeneratedTicket(int userId);

}