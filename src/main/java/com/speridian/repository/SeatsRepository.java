package com.speridian.repository;

import com.speridian.entity.Seats;

public interface SeatsRepository {

	void addSeat(Seats seat);

	Seats fetchSeat(int fsId, String fclass);

	void changeSeatStatus(Seats seat);

}