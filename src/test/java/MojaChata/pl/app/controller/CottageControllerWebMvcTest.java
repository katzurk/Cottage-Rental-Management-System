package MojaChata.pl.app.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import MojaChata.pl.app.config.SecurityConfig;
import MojaChata.pl.app.model.CityRepository;
import MojaChata.pl.app.model.CottageRepository;
import MojaChata.pl.app.model.RequestService;
import MojaChata.pl.app.model.User;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CottageController.class)
@Import(SecurityConfig.class)
public class CottageControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CottageRepository cottageRepository;
    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private RequestService requestService;

    @Test
    public void testMyCottagesRequestForNotLoggedUserShouldShowLoginPrompt() throws Exception {

        when(cottageRepository.findByOwnerId(anyLong())).thenReturn(List.of());

        mockMvc.perform(get("/my-cottages"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Log in to see your cottages!")));
    }

    @Test
    public void testMyCottagesRequestForLoggedUserShouldShowCottageTable() throws Exception {

        when(cottageRepository.findByOwnerId(anyLong())).thenReturn(List.of());

        mockMvc.perform(get("/my-cottages")
            .sessionAttr("loggedInUser", new User("name", "pass")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<table>")));
    }

    @Test
    public void testTryingToSaveCottageWitoutNameWillShowError() throws Exception {

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
        .sessionAttr("loggedInUser", new User("name", "pass")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<span class=\"error-message\">Name is mandatory</span>")));
    }

    @Test
    public void testCanSaveValidCottage() throws Exception {

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
            .sessionAttr("loggedInUser", new User("name", "pass")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/my-cottages"));
    }
}
