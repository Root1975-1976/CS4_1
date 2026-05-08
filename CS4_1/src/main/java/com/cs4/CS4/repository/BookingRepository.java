package com.cs4.CS4.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cs4.CS4.model.Booking;
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
}
