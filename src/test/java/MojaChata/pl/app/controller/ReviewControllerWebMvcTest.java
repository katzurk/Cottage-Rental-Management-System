package MojaChata.pl.app.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import MojaChata.pl.app.config.SecurityConfig;
import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.Review;
import MojaChata.pl.app.model.ReviewRepository;
import MojaChata.pl.app.model.User;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
@Import(SecurityConfig.class)

public class ReviewControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CottageRepository cottageRepository;
    @MockBean
    private ReviewRepository reviewRepository;


    @Test
    public void testReviewsEmpty() throws Exception {

        when(reviewRepository.findByCottageId(anyLong())).thenReturn(List.of());

        mockMvc.perform(get("/cottages/2/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No results")));
    }

    @Test
    public void testReviewsNotEmpty() throws Exception {
		Review revMock = Mockito.mock(Review.class);
		Mockito.when(revMock.getId()).thenReturn(1L);
		Mockito.when(revMock.getText()).thenReturn("Test review");
		Mockito.when(revMock.getAuthor()).thenReturn(new User("name", "pass"));


        when(reviewRepository.findByCottageId(anyLong())).thenReturn(List.of(revMock));

        mockMvc.perform(get("/cottages/2/reviews")
            .sessionAttr("loggedInUser", new User("name", "pass")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test review")));
    }

    @Test
    public void testTryingToSaveReviewWitoutGradeWillShowError() throws Exception {

        mockMvc.perform(post("/cottages/2/reviews/addReview")
            .accept(MediaType.TEXT_HTML)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content("text=Good."
                + "&grade=")
            .sessionAttr("loggedInUser", new User("name", "pass")))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("<span class=\"error-message\">grade is mandatory</span>")));
    }

    @Test
    public void testCanSaveValidReview() throws Exception {

        when(cottageRepository.findById(2L)).thenReturn(Optional.of(new Cottage()));

        mockMvc.perform(post("/cottages/2/reviews/addReview")
            .accept(MediaType.TEXT_HTML)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content("text=cool cottage"
                + "&grade=4")
            .sessionAttr("loggedInUser", new User("name", "pass")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cottages/2/reviews"));

        verify(reviewRepository).save(argThat(rev -> "cool cottage".equals(rev.getText()) && rev.getGrade() == 4));
    }
}
