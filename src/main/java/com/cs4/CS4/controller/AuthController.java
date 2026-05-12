package com.cs4.CS4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.cs4.CS4.model.Role;
import com.cs4.CS4.model.User;
import com.cs4.CS4.service.BookingService;
import com.cs4.CS4.service.RoomService;
import com.cs4.CS4.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;
    
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "register";
        }
        if(userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error","Email already registered");
            return "register";
        }
        if(user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session, 
    		HttpServletResponse response,Model model) {
        User user = userService.login(email, password);
        if(user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
        session.setAttribute("loggedUser", user);
        Cookie cookie = new Cookie("username", user.getName());
        cookie.setMaxAge(60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        if(user.getRole() == Role.ADMIN) {
            return "redirect:/admin/home";
        }
        return "redirect:/customer/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/admin/home")
    public String adminHome(Model model) {
        model.addAttribute("usersCount", userService.getAllUsers().size());
        model.addAttribute("roomsCount", roomService.getAllRooms().size());
        model.addAttribute("bookingsCount", bookingService.getAllBookings().size());
        return "admin-home";
    }

    @GetMapping("/customer/home")
    public String customerHome() {
        return "customer-home";
    }
    
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if(user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }
    
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute User updatedUser, HttpSession session) {
        User sessionUser = (User) session.getAttribute("loggedUser");
        if(sessionUser == null) {
            return "redirect:/login";
        }
        sessionUser.setName(updatedUser.getName());
        sessionUser.setEmail(updatedUser.getEmail());
        sessionUser.setPassword(updatedUser.getPassword());
        userService.saveUser(sessionUser);
        session.setAttribute("loggedUser", sessionUser);
        return "redirect:/profile";
    }
}