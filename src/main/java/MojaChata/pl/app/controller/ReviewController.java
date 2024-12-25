package MojaChata.pl.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.Review;
import MojaChata.pl.app.model.ReviewRepository;
import MojaChata.pl.app.model.User;

@Controller
public class ReviewController {
    Logger logger = LoggerFactory.getLogger(CottageController.class);
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/cottages/{cottageId}/reviews")
    public String showReviews(@SessionAttribute(value = "loggedInUser", required = false) User login,
                            @PathVariable("cottageId") long cottageId, Model model) {
        // if (login == null) {
        //     return "redirect:/login";
        // }
        // Cottage cottage = cottageRepository.findById(cottageId)
        //         .orElseThrow(() -> new RuntimeException("Invalid cottage Id: " + cottageId));


        List<Review> reviews = reviewRepository.findByCottageId(cottageId);

        model.addAttribute("reviews", reviews);
        model.addAttribute("cottageId", cottageId);
        return "cottage-reviews";
    }

    @GetMapping("/cottages/{cottageId}/reviews/addReview")
    public String showAddReview(@SessionAttribute(value = "loggedInUser", required = true) User login,
                            @PathVariable("cottageId") long cottageId, Model model, Review review) {
        // Cottage cottage = cottageRepository.findById(cottageId)
        //         .orElseThrow(() -> new RuntimeException("Invalid cottage Id: " + cottageId));



        model.addAttribute("cottageId", cottageId);
        return "add-review";
    }

    @PostMapping("/cottages/{cottageId}/reviews/addReview")
    public String addReview(@SessionAttribute(value = "loggedInUser", required = true) User author,
                            @PathVariable("cottageId") long cottageId, Model model, Review review, BindingResult result) {

        if (result.hasErrors()) {
            logger.warn("-- add review has errors: " + result.getAllErrors());
            return "add-cottage";
        }

        Cottage cottage = cottageRepository.findById(cottageId)
                .orElseThrow(() -> new RuntimeException("Invalid cottage Id: " + cottageId));

        review.setCottage(cottage);
        review.setAuthor(author);
        review.setDatePosted( new java.sql.Date(System.currentTimeMillis()));

        logger.info("about to save review: " + review);
        reviewRepository.save(review);
        return "redirect:/cottages/" + cottageId + "/reviews";
    }

}
