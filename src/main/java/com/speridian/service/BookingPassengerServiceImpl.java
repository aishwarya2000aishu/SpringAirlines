package com.speridian.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.speridian.dto.AddBookingDto;
import com.speridian.dto.GenerateTicketDto;
import com.speridian.dto.ViewBookingDto;
import com.speridian.entity.Booking;
import com.speridian.entity.FlightSchedule;
import com.speridian.entity.Passengers;
import com.speridian.entity.Users;
import com.speridian.exception.BookingServiceException;
import com.speridian.repository.BookingPassengerRepository;
import com.speridian.repository.SchedulesRepository;
import com.speridian.repository.SeatsRepository;
import com.speridian.repository.UsersRepository;
@Service
public class BookingPassengerServiceImpl implements BookingPassengerService {

	@Autowired
	private BookingPassengerRepository bookingRepo;
	
	@Autowired
	private SchedulesRepository schedulesRepo;
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private SeatsRepository seatsRepo;
	
	@Override
	@Transactional
	public void addBookingAndPassengers(AddBookingDto bookingDto, int id) {
		try {
		Booking booking = new Booking();
		FlightSchedule fs = schedulesRepo.fetchSingleFlightScheduleById(id);
		Users user = userRepo.fetchUserById(bookingDto.getUserId());
		booking.setFlightSchedule(fs);
		booking.setUser(user);
		booking.setBookingDateTime(LocalDateTime.now());
		booking.setTotalPassengers(bookingDto.getNoOfPassengers());
		booking.setBookingAmount(bookingDto.getAmount());
		booking.setStatus("CONFIRMED");
		
		bookingRepo.addBooking(booking);
		
		int bid = bookingRepo.fetchBookingScheduleId();
		booking.setId(bid);
		
		Passengers passenger = null;
		
		for(int i=0; i<bookingDto.getNoOfPassengers(); i++) {
			passenger = new Passengers();
			
			BeanUtils.copyProperties(bookingDto.getPassengersDetails().get(i), passenger);
			passenger.setBooking(booking);
			
			
			passenger.setSeat(seatsRepo.fetchSeat(fs.getId(), bookingDto.getFclass()));
			
			
			seatsRepo.changeSeatStatus(passenger.getSeat());
			bookingRepo.addPassenger(passenger);
		}
		
		if(bookingDto.getFclass().equalsIgnoreCase("BUSINESS"))
			fs.setBusinessSeatsAvailable(fs.getBusinessSeatsAvailable() - bookingDto.getNoOfPassengers());
		else
			fs.setEconomySeatsAvailable(fs.getEconomySeatsAvailable() - bookingDto.getNoOfPassengers());
		}
		catch(Exception e) {
			throw new BookingServiceException("Booking Failed!");
		}
		
	}
	
	@Override
	@Transactional
	public List<Object[]> getAllPassengers(int id) {
		return bookingRepo.viewBooking(id);
	}
	
	@Override
	@Transactional
	public GenerateTicketDto fetchGeneratedTicket(int userId) {
		
		GenerateTicketDto ticket = new GenerateTicketDto();
		List<Object[]> allBookings = bookingRepo.viewBooking(userId);
		
		ViewBookingDto viewBookDt = new ViewBookingDto();
		
		viewBookDt.setId((int)allBookings.get(0)[0]);
		viewBookDt.setBookingDateTime((LocalDateTime)allBookings.get(0)[1]);
		viewBookDt.setTotalPassengers((int) allBookings.get(0)[2]);
		viewBookDt.setBookingAmount((double)allBookings.get(0)[3]);
		viewBookDt.setStatus((String)allBookings.get(0)[4]);
		viewBookDt.setDepart((LocalDateTime)allBookings.get(0)[5]);
		viewBookDt.setArrive((LocalDateTime)allBookings.get(0)[6]);
		viewBookDt.setSource((String)allBookings.get(0)[7]);
		viewBookDt.setDestination((String)allBookings.get(0)[8]);
		
		ticket.setBooking(viewBookDt);
		
		ticket.setPassengers(bookingRepo.fetchAllPassengersByBookingId(viewBookDt.getId()));
		
		return ticket;
		
	}
	
	
}
