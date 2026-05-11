package com.cs4.CS4.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs4.CS4.model.Booking;
import com.cs4.CS4.model.BookingStatus;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.model.User;
import com.cs4.CS4.repository.BookingRepository;
import com.cs4.CS4.repository.RoomRepository;
@Service
public class BookingService {
	    @Autowired
	    private BookingRepository bookingRepo;
	    
	    @Autowired
	    private RoomRepository roomRepo;
	    
	    public Booking bookRoom(Booking booking) {
	        Room room = roomRepo.findById((long) booking.getRoom().getId())
	                .orElseThrow(() -> new RuntimeException("Room not found"));
	        if (!room.isAvailable()) {
	            throw new RuntimeException("Room not available");
	        }
	        room.setAvailable(false);
	        roomRepo.save(room);
	        booking.setStatus(BookingStatus.CONFIRMED);
	        return bookingRepo.save(booking);
	    }
	    
	    public List<Booking> getAllBookings() {
	        return bookingRepo.findAll();
	    }
	    
	    public void cancelBooking(Long id) {
	        bookingRepo.deleteById(id);
	    }
	    
	    public List<Booking> getBookingsByUser(User user) {
	        return bookingRepo.findByUser(user);
	    }
	}


