package com.cs4.CS4.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs4.CS4.model.Booking;
import com.cs4.CS4.model.User;
public interface BookingRepository extends JpaRepository<Booking, Long> {
	  List<Booking> findByUser(User user);
}
