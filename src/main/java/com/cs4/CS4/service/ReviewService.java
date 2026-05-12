package com.cs4.CS4.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs4.CS4.model.Review;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.model.User;
import com.cs4.CS4.repository.ReviewRepository;
@Service
public class ReviewService {
	@Autowired private ReviewRepository reviewRepo;

    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }

    public boolean hasReviewed(User user, Room room) {
        return reviewRepo.existsByUserAndRoom(user, room);
    }

    public List<Review> getReviewsByRoom(Room room) {
        return reviewRepo.findByRoom(room);
    }
}
