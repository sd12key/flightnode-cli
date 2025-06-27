package org.alvio.flightcli.app;

import org.alvio.flightcli.client.CityClient;
import org.alvio.flightcli.domain.Airport;
import org.alvio.flightcli.domain.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.alvio.flightcli.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightCliAppTest {

    private FlightCliApp app;
    private CityClient mockCityClient;

    private final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        mockCityClient = Mockito.mock(CityClient.class);
        app = new FlightCliApp();
        app.setCityClient(mockCityClient);

        System.setOut(new PrintStream(outputBuffer));  // capture console output
    }

    @Test
    public void testRunCityWithAirportsQuery() throws Exception {
        List<City> mockCities = List.of(NY_W_AIR, LA_W_AIR, MC_W_AIR);
        Mockito.when(mockCityClient.fetchCitiesWithAirports()).thenReturn(mockCities);

        List<String> report = app.runCityAirportQuery();

        assertEquals(3, report.size());
        assertTrue(report.get(0).contains("New York"));
        assertTrue(report.get(0).contains("JFK"));
        assertTrue(report.get(1).contains("Los Angeles"));
        assertTrue(report.get(1).contains("LAX"));
        assertTrue(report.get(2).contains("Macon"));
        assertTrue(report.get(2).contains("157346"));
        assertTrue(report.get(2).contains("[id:11]"));

    }
}
