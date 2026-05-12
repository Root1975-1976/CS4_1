package com.cs4.CS4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cs4.CS4.model.*;
import com.cs4.CS4.service.BookingService;
import com.cs4.CS4.service.ReviewService;
import jakarta.servlet.http.HttpSession;
@Controller
public class ReviewController {
	    @Autowired 
	    private ReviewService  reviewService;
	    
	    @Autowired 
	    private BookingService bookingService;

	    @GetMapping("/review/{bookingId}")
	    public String showReviewPage(@PathVariable Long bookingId, Model model) {
	        Booking booking = bookingService.getBookingById(bookingId);
	        model.addAttribute("booking", booking);
	        return "review";
	    }

	    @PostMapping("/review/{bookingId}")
	    public String submitReview(
	            @PathVariable Long bookingId,
	            @RequestParam("rating")  int    rating,
	            @RequestParam("comment") String comment, HttpSession session) {
	        Booking booking = bookingService.getBookingById(bookingId);
	        User  user  = (User) session.getAttribute("loggedUser");

	        Review review = new Review();
	        review.setUser(user);
	        review.setRoom(booking.getRoom());
	        review.setRating(rating);
	        review.setComment(comment);

	        reviewService.saveReview(review);
	        return "redirect:/history";
	    }
}
