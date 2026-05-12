package com.cs4.CS4.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cs4.CS4.model.Review;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.model.User;
public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findByRoom(Room room);
    List<Review> findByUser(User user);
    boolean existsByUserAndRoom(User user, Room room);
}
