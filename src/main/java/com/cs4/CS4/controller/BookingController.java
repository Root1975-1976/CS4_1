package com.cs4.CS4.controller;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cs4.CS4.model.*;
import com.cs4.CS4.service.BookingService;
import com.cs4.CS4.service.RoomService;
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
        User user = (User) session.getAttribute("loggedUser");
        model.addAttribute("bookings", bookingService.getBookingsByUser(user));
        return "history";
    }

    @GetMapping("/book-room/{id}")
    public String showBookingPage(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getRoomById(id));
        return "book-room";
    }

    @PostMapping("/book-room/{id}")
    public String confirmBooking(
            @PathVariable Long id,
            @RequestParam("checkIn")  String checkIn,
            @RequestParam("checkOut") String checkOut,
            @RequestParam (value = "guests", defaultValue = "1") int guests, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        Room room = roomService.getRoomById(id);

        try {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setRoom(room);
            booking.setCheckIn(LocalDate.parse(checkIn));
            booking.setCheckOut(LocalDate.parse(checkOut));
            booking.setGuests(guests);

            Booking saved = bookingService.bookRoom(booking);
            return "redirect:/payment/" + saved.getId();

        } catch (RuntimeException e) {
            model.addAttribute("room", room);
            model.addAttribute("error", e.getMessage());
            return "book-room";
        }
    }

    @GetMapping("/cancel-booking/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/history";
    }
}