package com.cs4.CS4.service;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cs4.CS4.model.*;
import com.cs4.CS4.repository.BookingRepository;
import com.cs4.CS4.repository.RoomRepository;
@Service
public class BookingService {

    @Autowired 
    private BookingRepository bookingRepo;
    
    @Autowired 
    private RoomRepository roomRepo;
    
    @Autowired
    private EmailService emailService;

    @Transactional
    public Booking bookRoom(Booking booking) {
        Room room = roomRepo.findById(booking.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        if (!room.isAvailable()) {
            throw new RuntimeException("Room is not available");
        }

        long nights = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
        if (nights <= 0) throw new RuntimeException("Check-out must be after check-in");

        booking.setTotalPrice(room.getPrice() * nights);
        booking.setStatus(BookingStatus.PENDING);  

        room.setAvailable(false);
        roomRepo.save(room);

        return bookingRepo.save(booking);
    }

    @Transactional
    public void confirmBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepo.save(booking);

        try {
            String subject = "Booking Confirmed — Room " + booking.getRoom().getRoomNumber();
            String body = String.format(
                "Dear %s,\n\n" +
                "Your booking has been confirmed!\n\n" +
                "Room Number : %d\n" +
                "Room Type   : %s\n" +
                "Check-In    : %s\n" +
                "Check-Out   : %s\n" +
                "Guests      : %d\n" +
                "Total Price : EGP %.2f\n\n" +
                "Thank you for choosing our hotel!\n",
                booking.getUser().getName(),
                booking.getRoom().getRoomNumber(),
                booking.getRoom().getType(),
                booking.getCheckIn(),
                booking.getCheckOut(),
                booking.getGuests(),
                booking.getTotalPrice()
            );
            emailService.sendEmail(booking.getUser().getEmail(), subject, body);
        } catch (Exception e) {
            System.err.println("Email sending failed: " + e.getMessage());
        }
    }

    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        Room room = booking.getRoom();
        room.setAvailable(true);
        roomRepo.save(room);

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id).orElse(null);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepo.findByUser(user);
    }
}

