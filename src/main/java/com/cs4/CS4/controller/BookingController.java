package com.cs4.CS4.controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cs4.CS4.service.RoomService;
import com.cs4.CS4.model.Booking;
import com.cs4.CS4.model.BookingStatus;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.model.User;
import com.cs4.CS4.service.BookingService;
import jakarta.servlet.http.HttpSession;
@Controller
public class BookingController {
	   @Autowired
	    private BookingService bookingService;

	   @Autowired
	   private RoomService roomService;
	   
	    @GetMapping("/bookings")
	    public String getBookings(Model model) {
	        model.addAttribute("bookings", bookingService.getAllBookings());
	        return "bookings";
	    }

	    @GetMapping("/history")
	    public String history(HttpSession session, Model model) {
	        User user =(User) session.getAttribute("loggedUser");
	        model.addAttribute("bookings",bookingService.getBookingsByUser(user));
	        return "history";
	    }
	    
	    @GetMapping("/book-room/{id}")
	    public String bookRoom(@PathVariable Long id, HttpSession session) {
	        User user =(User) session.getAttribute("loggedUser");
	        Room room =roomService.getRoomById(id);
	        Booking booking = new Booking();
	        booking.setUser(user);
	        booking.setRoom(room);
	        booking.setCheckIn(LocalDate.now());
	        booking.setCheckOut(LocalDate.now().plusDays(2));
	        booking.setStatus(BookingStatus.CONFIRMED);
	        bookingService.bookRoom(booking);
	        return "redirect:/history";
	    }
	}

