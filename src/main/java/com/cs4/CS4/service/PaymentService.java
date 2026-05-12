package com.cs4.CS4.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cs4.CS4.model.*;
import com.cs4.CS4.repository.PaymentRepository;
@Service
public class PaymentService {
	 @Autowired private PaymentRepository paymentRepo;
	    @Autowired private BookingService    bookingService;

	    @Transactional
	    public Payment processPayment(Long bookingId, PaymentMethod method) {
	        Booking booking = bookingService.getBookingById(bookingId);
	        if (booking == null) throw new RuntimeException("Booking not found");
	        Payment payment = new Payment();
	        payment.setBooking(booking);
	        payment.setAmount(booking.getTotalPrice());
	        payment.setMethod(method);
	        payment.setStatus(PaymentStatus.PAID);

	        Payment saved = paymentRepo.save(payment);

	        bookingService.confirmBooking(bookingId);
	        return saved;
	    }

	    public Payment getPaymentByBooking(Booking booking) {
	        return paymentRepo.findByBooking(booking);
	    }
}
