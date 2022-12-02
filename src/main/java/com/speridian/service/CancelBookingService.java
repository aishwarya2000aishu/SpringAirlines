package com.speridian.service;

import javax.transaction.Transactional;

public interface CancelBookingService {

	void cancelBooking(int bookingId);

}