package com.cs4.CS4.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Review {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
 private int rating;
	    private String comment;
	    @ManyToOne
	    private User user;
 @ManyToOne
	    private Room room;
 public Long getId() {
	return id;
 }
 public void setId(Long id) {
	this.id = id;
 }
 public int getRating() {
	return rating;
 }
 public void setRating(int rating) {
	this.rating = rating;
 }
 public String getComment() {
	return comment;
 }
 public void setComment(String comment) {
	this.comment = comment;
 }
 public User getUser() {
	return user;
 }
 public void setUser(User user) {
	this.user = user;
 }
 public Room getRoom() {
	return room;
 }
 public void setRoom(Room room) {
	this.room = room;
 }
	}


