package com.cs4.CS4.model;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.cs4.CS4.model.RoomType;
@Entity
public class Room {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private int roomNumber;
	    @Enumerated(EnumType.ORDINAL)	 
	    private RoomType type;
	    private double price;
	    private boolean available;
	    @OneToMany(mappedBy = "room")
	    private List<Booking> bookings;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public int getRoomNumber() {
			return roomNumber;
		}
		public void setRoomNumber(int roomNumber) {
			this.roomNumber = roomNumber;
		}
		public RoomType getType() {
			return type;
		}
		public void setType(RoomType type) {
			this.type = type;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public boolean isAvailable() {
			return available;
		}
		public void setAvailable(boolean available) {
			this.available = available;
		}
		public List<Booking> getBookings() {
			return bookings;
		}
		public void setBookings(List<Booking> bookings) {
			this.bookings = bookings;
		}
	}
	

