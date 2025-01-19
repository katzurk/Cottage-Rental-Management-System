package MojaChata.pl.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import MojaChata.pl.app.model.User;

@SpringBootTest
@AutoConfigureMockMvc
class CottageControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMyCottagesRequestForNotLoggedUserShouldShowLoginPrompt() throws Exception {

        mockMvc.perform(get("/my-cottages"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Log in to see your cottages!")));
    }

    @Test
    public void testMyCottagesRequestForLoggedUserShouldShowCottageTable() throws Exception {

		User userMock = Mockito.mock(User.class);
		Mockito.when(userMock.getId()).thenReturn(1L);

        mockMvc.perform(get("/my-cottages")
            .sessionAttr("loggedInUser", userMock))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<td >Micro Cottage</td>")))
                .andExpect(content().string(containsString("<td >The Grand Villa</td>")))
                .andExpect(content().string(containsString("<td >Merry Cottage</td>")))
                .andExpect(content().string(containsString("<td >Merry House</td>")));
    }

}

