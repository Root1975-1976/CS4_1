package com.cs4.CS4.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Room {
	

	    @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int roomNumber;

	    @Enumerated(EnumType.STRING)
	    private RoomType type;

	    private double price;

	    private boolean available;

	    @OneToMany(mappedBy = "room")
	    private List<Booking> bookings;
	}
	

