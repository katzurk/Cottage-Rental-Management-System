package MojaChata.pl.app.controller;


import MojaChata.pl.app.model.*;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;



@Controller
@SessionAttributes("loggedInUser")
public class UserController {
    @Autowired
    private UserRepository loginRepository;
    
    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
    @RequestParam("password") String password,
    Model model, HttpSession session) {
        Optional<User> userOpt = loginRepository.findByUsername(username);

        if (userOpt.isPresent() && bc.matches(password, userOpt.get().getPasswordHash())) {

            model.addAttribute("loggedInUser", userOpt.get());
            // System.out.println(session.getAttribute("loggedInUser"));
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Invalid username or password.");

            return "login";
        }
    }

    @GetMapping ("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(User login, @RequestParam("username") String username,
                           @RequestParam("passwordHash") String password,
                           @RequestParam("confirmPassword") String confirmPassword,
                           Model model) {
    if (loginRepository.findByUsername(username).isPresent()) {
        model.addAttribute("error", "Username already exists. Please choose a different one.");
        return "register";
    }

    if (!password.equals(confirmPassword)) {
        model.addAttribute("error", "Passwords do not match.");
        return "register";
    }
    login.setPasswordHash(bc.encode(password));
    loginRepository.save(login);

    return "redirect:/login";
}


    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/index";
    }

}