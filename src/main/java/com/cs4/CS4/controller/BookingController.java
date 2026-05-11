package com.cs4.CS4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cs4.CS4.service.BookingService;
@Controller
public class BookingController {
	    @Autowired
	    private BookingService bookingService;
	    
	    @GetMapping("/bookings")	
	    public String getBookings(Model model) {
	        model.addAttribute("bookings", bookingService.getAllBookings());
	        return "bookings";
	    }
	}

