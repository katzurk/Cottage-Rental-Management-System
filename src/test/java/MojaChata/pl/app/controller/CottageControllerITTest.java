package MojaChata.pl.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;

import MojaChata.pl.app.model.Cottage;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.User;
import MojaChata.pl.app.model.UserRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class CottageControllerITTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CottageRepository cottageRepository;

    @Test
    public void testMyCottagesRequestForNotLoggedUserShouldShowLoginPrompt() throws Exception {

        mockMvc.perform(get("/my-cottages"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Log in to see your cottages!")));
    }

    @Test
    public void testMyCottagesRequestForLoggedUserShouldShowCottageTable() throws Exception {
		User user = userRepository.findById(1L).get();

        mockMvc.perform(get("/my-cottages")
            .sessionAttr("loggedInUser", user))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<td >Micro Cottage</td>")))
                .andExpect(content().string(containsString("<td >The Grand Villa</td>")))
                .andExpect(content().string(containsString("<td >Merry Cottage</td>")))
                .andExpect(content().string(containsString("<td >Merry House</td>")));
    }

    @Test
    public void testTryingToSaveCottageWitoutNameWillShowError() throws Exception {
		User user = userRepository.findById(1L).get();

        mockMvc.perform(post("/addcottage")
            .accept(MediaType.TEXT_HTML)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content("name="
                + "&address.street=streete"
                + "&address.postalCode=0000"
                + "&address.city.id=16"
                + "&size_m2=70"
                + "&roomsNumber=7"
                + "&bathroomsNumber=10"
                + "&maxPeopleNum=3"
                + "&minPricePerDay=77.7")
            .sessionAttr("loggedInUser", user))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("<span class=\"error-message\">Name is mandatory</span>")));
    }

    @Test
    public void testCanSaveValidCottage() throws Exception {
		User user = userRepository.findById(1L).get();

        mockMvc.perform(post("/addcottage")
            .accept(MediaType.TEXT_HTML)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .content("name=new-great-cottage-name"
                + "&address.street=streete"
                + "&address.postalCode=0000"
                + "&address.city.id=16"
                + "&size_m2=70"
                + "&roomsNumber=7"
                + "&bathroomsNumber=10"
                + "&maxPeopleNum=3"
                + "&minPricePerDay=77.7")
            .sessionAttr("loggedInUser", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/my-cottages"));
    }

    @Test
    public void testRedirectToLoginWhenNotLoggedInAndRemovingCottage() throws Exception{
        mockMvc.perform(get("/delete/1"))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login"));
    }

    @Test
    @Transactional
    public void testRemoveCottageOk() throws Exception{
		User user = userRepository.findById(1L).get();

        assertTrue(cottageRepository.findById(1L).isPresent());

        mockMvc.perform(get("/delete/1")
        .sessionAttr("loggedInUser", user))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/my-cottages"));

        assertTrue(cottageRepository.findById(1L).isEmpty());
    }

}

