package org.example.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.country.Country;
import org.example.country.CountryController;
import org.example.country.CountryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CountryController.class)
@AutoConfigureMockMvc(addFilters = false) // to disable security filters
public class CountryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CountryService service;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("GET /countries")
    public void testIndex() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country spain = new Country( "Spain");
        Country england = new Country("England");
        countries.add(spain);
        countries.add(england);

        when(service.getAll()).thenReturn(countries);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/countries")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString(spain.getName()));
        assertThat(response.getContentAsString(), containsString(england.getName()));
        assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(countries)));
    }

    @Test
    void testControllerHasPathToPostCountry() throws Exception {

        Country irland = new Country("Irland");

        String json = mapper.writeValueAsString(irland);

        when(service.store(Mockito.any(Country.class))).thenReturn(irland);
        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/countries")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(201));
        assertThat(response.getContentAsString(), is(json));

    }

}
