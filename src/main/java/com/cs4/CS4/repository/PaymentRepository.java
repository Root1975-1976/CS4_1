package com.cs4.CS4.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cs4.CS4.model.Booking;
import com.cs4.CS4.model.Payment;
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBooking(Booking booking);
}
