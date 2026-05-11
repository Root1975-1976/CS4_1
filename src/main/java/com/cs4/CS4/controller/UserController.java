package com.cs4.CS4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.cs4.CS4.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {
	@Autowired
    private UserService userService;
	
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
}
