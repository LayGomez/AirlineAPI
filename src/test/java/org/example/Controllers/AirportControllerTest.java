package org.example.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.airport.Airport;
import org.example.airport.AirportController;
import org.example.airport.AirportMapper;
import org.example.airport.AirportServices;
import org.example.airport.dtos.AirportRequest;
import org.example.airport.dtos.AirportResponse;
import org.example.country.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers =  AirportController.class)
@AutoConfigureMockMvc(addFilters = false)
class AirportControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AirportServices services;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("GET /airports")

    public void testGetAirports() throws Exception{
        Country spain = new Country("Spain");

        List<Airport> airportList = new ArrayList<>();
        Airport valencia = new Airport("Aeropuerto Internacional de Valencia", spain);
        Airport madrid =  new Airport("Aeropuerto Adolfo Suárez Madrid-Barajas", spain);

        airportList.add(valencia);
        airportList.add(madrid);

        List<AirportResponse> airportResponses= airportList.stream()
                .map(AirportMapper::toResponse)
                .toList();

        when(services.getAllAirports()).thenReturn(airportResponses);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/airports")
                .accept(MediaType.APPLICATION_JSON)
                .content("application/json"))
            .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), containsString(valencia.getName()));
        assertThat(response.getContentAsString(), containsString(madrid.getName()));
        assertThat(response.getContentAsString(), equalTo(mapper.writeValueAsString(airportResponses)));
    }

    @Test
    void testControllerHasPathToPostCountry() throws Exception{
        Country spain = new Country("Spain");

        Airport valencia = new Airport("Aeropuerto Internacional de Valencia", spain);
        String json = mapper.writeValueAsString(valencia);

        AirportResponse airportResponse= AirportMapper.toResponse(valencia);

        when(services.createAirport(Mockito.any(AirportRequest.class))).thenReturn(airportResponse);
        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/airports")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();
    }

    @Test
    public void testGetAirportById() throws Exception {
        Long airportId = 1L;
        Country spain = new Country("Spain");
        Airport airport = new Airport("Aeropuerto de Sevilla", spain);
        AirportResponse response = AirportMapper.toResponse(airport);

        when(services.findAirportById(airportId)).thenReturn(response);

        mockMvc.perform(get("/api/v1/airports/{id}", airportId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(airport.getName()))
                .andExpect(jsonPath("$.country").value(spain.getName()));
    }

    @Test
    public void testUpdateAirport() throws Exception {
        Long airportId = 1L;
        Country spain = new Country("Spain");
        Airport updatedAirport = new Airport("Aeropuerto Internacional de Sevilla", spain);
        AirportResponse response = AirportMapper.toResponse(updatedAirport);

        when(services.updateAirport(eq(airportId), ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/airports/{id}", airportId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedAirport)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedAirport.getName()))
                .andExpect(jsonPath("$.country").value(spain.getName()));
    }

    @Test
    public void testDeleteAirport() throws Exception {
        Long airportId = 1L;

        doNothing().when(services).deleteDestinationById(airportId);

        mockMvc.perform(delete("/api/v1/airports/{id}", airportId))
                .andExpect(status().isOk());
    }

}