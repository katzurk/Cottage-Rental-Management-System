package MojaChata.pl.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import MojaChata.pl.app.model.ReviewRepository;
import MojaChata.pl.app.model.User;
import MojaChata.pl.app.model.UserRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerITTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    public void testReviewsEmpty() throws Exception {

        mockMvc.perform(get("/cottages/5/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No results")));
    }

    @Test
    public void testReviewsNotEmpty() throws Exception {

        mockMvc.perform(get("/cottages/2/reviews")
            .sessionAttr("loggedInUser", new User("name", "pass")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("great view, comfy beds. I recommend it to everyone.")))
                .andExpect(content().string(containsString("fantastic place")))
                .andExpect(content().string(containsString("a bit high price, but definitely worth it")));
    }

    @Test
    public void testCanSaveValidReview() throws Exception {
		User user = userRepository.findById(1L).get();

        mockMvc.perform(post("/cottages/1/reviews/addReview")
            .accept(MediaType.TEXT_HTML)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content("text=cool cottage"
                + "&grade=4")
            .sessionAttr("loggedInUser", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cottages/1/reviews"));
    }

    @Test
    public void testRedirectToLoginWhenNotLoggedInAndRemovingReviews() throws Exception{
        mockMvc.perform(get("/cottages/1/reviews/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login"));
    }

    @Test
    @Transactional
    public void testRemoveReviewOk() throws Exception{
		User user = userRepository.findById(1L).get();

        assertTrue(reviewRepository.findById(1L).isPresent());

        mockMvc.perform(get("/cottages/1/reviews/delete/1")
        .sessionAttr("loggedInUser", user))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/cottages/1/reviews"));

        assertTrue(reviewRepository.findById(1L).isEmpty());
    }
}

