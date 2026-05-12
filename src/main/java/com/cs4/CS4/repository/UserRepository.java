package com.cs4.CS4.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cs4.CS4.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
