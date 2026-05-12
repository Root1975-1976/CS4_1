package com.cs4.CS4.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private int guests;
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Long getId(){
    	return id;
    	}
    public void setId(Long id){
    	this.id = id; 
    	}
    public User getUser(){
    	return user;
    	}
    public void setUser(User user){ 
    	this.user = user;
    	}
    public Room getRoom(){ 
    	return room;
    	}
    public void setRoom(Room room){ 
    	this.room = room; 
    	}
    public LocalDate getCheckIn(){ 
    	return checkIn; }
    public void setCheckIn(LocalDate checkIn)    { 
    	this.checkIn = checkIn;
    	}
    public LocalDate getCheckOut(){ 
    	return checkOut;
    	}
    public void setCheckOut(LocalDate checkOut)  { 
    	this.checkOut = checkOut; 
    	}
    public int getGuests(){ 
    	return guests;
    	}
    public void setGuests(int guests){ 
    	this.guests = guests; 
    	}
    public double getTotalPrice(){ 
    	return totalPrice;
    	}
    public void setTotalPrice(double totalPrice){ 
    	this.totalPrice = totalPrice;
    	}
    public BookingStatus getStatus(){
    	return status; 
    	}
    public void setStatus(BookingStatus status){ 
    	this.status = status; 
    	}
}

