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

import java.util.Optional;



@Controller
public class ReviewsController {

    @GetMapping("/reviews")
    public String showLoginForm() {
        return "reviews";
    }
}