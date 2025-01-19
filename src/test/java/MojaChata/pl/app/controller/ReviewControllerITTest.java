package MojaChata.pl.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import MojaChata.pl.app.model.User;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerITTest {

    @Autowired
    private MockMvc mockMvc;

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
}

