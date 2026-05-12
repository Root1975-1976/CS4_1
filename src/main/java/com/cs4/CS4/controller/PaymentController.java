package com.cs4.CS4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cs4.CS4.model.*;
import com.cs4.CS4.service.BookingService;
import com.cs4.CS4.service.PaymentService;
@Controller
public class PaymentController {
	  @Autowired private PaymentService paymentService;
	    @Autowired private BookingService bookingService;

	    @GetMapping("/payment/{bookingId}")
	    public String showPaymentPage(@PathVariable Long bookingId, Model model) {
	        Booking booking = bookingService.getBookingById(bookingId);
	        model.addAttribute("booking", booking);
	        model.addAttribute("methods", PaymentMethod.values());
	        return "payment";
	    }

	    @PostMapping("/payment/{bookingId}")
	    public String processPayment(
	            @PathVariable Long bookingId,
	            @RequestParam("method") PaymentMethod method, Model model) {
	        try {
	            paymentService.processPayment(bookingId, method);
	            return "redirect:/history";
	        } catch (Exception e) {
	            Booking booking = bookingService.getBookingById(bookingId);
	            model.addAttribute("booking", booking);
	            model.addAttribute("methods", PaymentMethod.values());
	            model.addAttribute("error", "Payment failed: " + e.getMessage());
	            return "payment";
	        }
	    }
}
