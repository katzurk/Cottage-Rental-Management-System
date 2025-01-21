package MojaChata.pl.app.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import MojaChata.pl.app.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CottageService cottageService;

    @Test
    public void testSearchFormDisplayedCorrectly() throws Exception {
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-cottage"))
                .andExpect(content().string(containsString("Enter country")))
                .andExpect(content().string(containsString("Enter city")))
                .andExpect(content().string(containsString("Enter min price")));
    }

    @Test
    public void testSearchResultsDisplayedCorrectly() throws Exception {
        Country mockCountry = Mockito.mock(Country.class);
        when(mockCountry.getName()).thenReturn("Poland");

        City mockCity = Mockito.mock(City.class);
        when(mockCity.getName()).thenReturn("Warsaw");
        when(mockCity.getCountry()).thenReturn(mockCountry);

        Address mockAddress = Mockito.mock(Address.class);
        when(mockAddress.getStreet()).thenReturn("Main Street");
        when(mockAddress.getPostalCode()).thenReturn("00-001");
        when(mockAddress.getCity()).thenReturn(mockCity);

        Cottage mockCottage = Mockito.mock(Cottage.class);
        when(mockCottage.getName()).thenReturn("Micro Cottage");
        when(mockCottage.getAddress()).thenReturn(mockAddress);
        when(mockCottage.getSize_m2()).thenReturn(50);
        when(mockCottage.getRoomsNumber()).thenReturn(2);
        when(mockCottage.getBathroomsNumber()).thenReturn(1);
        when(mockCottage.getMaxPeopleNum()).thenReturn(4);
        when(mockCottage.getMinPricePerDay()).thenReturn(BigDecimal.valueOf(50));

        when(cottageService.searchCottage(Mockito.argThat(searchDTO ->
                "Poland".equals(searchDTO.getAddressCountry()))))
                .thenReturn(Collections.singletonList(mockCottage));

        // when(cottageService.searchCottage(Mockito.argThat(searchDTO ->
        //         !"Poland".equals(searchDTO.getAddressCountry()))))
        //         .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/search")
                        .param("addressCountry", "Poland"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-cottage"))
                .andExpect(content().string(containsString("Micro Cottage")))
                .andExpect(content().string(containsString("Poland")))
                .andExpect(content().string(containsString("Warsaw")))
                .andExpect(content().string(containsString("Main Street")))
                .andExpect(content().string(containsString("50")));
    }

    @Test
    public void testSearchNoResultsDisplayedCorrectly() throws Exception {
        mockMvc.perform(get("/search")
                        .param("addressCountry", "wwwwwwwwwwww"))
                .andExpect(status().isOk())
                .andExpect(view().name("search-cottage"))
                .andExpect(content().string(containsString("No results")));
    }

}

