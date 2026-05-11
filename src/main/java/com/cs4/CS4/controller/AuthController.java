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
import com.cs4.CS4.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result) {
        if(result.hasErrors()) {
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
}