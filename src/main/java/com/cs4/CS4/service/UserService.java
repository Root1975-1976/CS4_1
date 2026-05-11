package com.cs4.CS4.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs4.CS4.model.User;
import com.cs4.CS4.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    
    public User saveUser(User user) {
        return userRepo.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
    public User login(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }
}
