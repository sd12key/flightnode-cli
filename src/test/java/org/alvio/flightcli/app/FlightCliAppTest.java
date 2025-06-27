package org.alvio.flightcli.app;

import org.alvio.flightcli.client.AircraftClient;
import org.alvio.flightcli.client.CityClient;
import org.alvio.flightcli.client.PassengerClient;
import org.alvio.flightcli.domain.Aircraft;
import org.alvio.flightcli.domain.City;
import org.alvio.flightcli.domain.Passenger;
import org.alvio.flightcli.util.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.alvio.flightcli.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class FlightCliAppTest {

    private FlightCliApp app;
    private CityClient mockCityClient;
    private AircraftClient mockAircraftClient;
    private PassengerClient mockPassengerClient;

    private final ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        mockCityClient = Mockito.mock(CityClient.class);
        mockAircraftClient = Mockito.mock(AircraftClient.class);
        mockPassengerClient = Mockito.mock(PassengerClient.class);

        app = new FlightCliApp();
        app.setCityClient(mockCityClient);
        app.setAircraftClient(mockAircraftClient);
        app.setPassengerClient(mockPassengerClient);
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

    @Test
    public void testRunAircraftWithAirportsQuery() throws Exception {
        List<Aircraft> mockAircrafts = List.of(
                TestConstants.UA_737_W_AIR,
                TestConstants.UA_757_W_AIR,
                TestConstants.UA_787_W_AIR
        );
        Mockito.when(mockAircraftClient.fetchAircraftsWithAirports()).thenReturn(mockAircrafts);

        List<String> report = app.runAircraftAirportQuery();

        assertEquals(3, report.size());

        assertTrue(report.get(0).contains("Boeing 737-800"));
        assertTrue(report.get(0).contains("ORD"));
        assertTrue(report.get(0).contains("LAX"));

        assertTrue(report.get(1).contains("Boeing 757-200"));
        assertTrue(report.get(1).contains("SEA"));

        assertTrue(report.get(2).contains("Boeing 787-9"));
        assertTrue(report.get(2).contains("assigned"));
    }

    @Test
    public void testRunPassengerWithAircraftsQuery() throws Exception {
        List<Passenger> mockPassengers = List.of(
                TestConstants.MARY_W_PLANES,
                TestConstants.JAMES_W_PLANES,
                TestConstants.SOPHIA_W_PLANES
        );
        Mockito.when(mockPassengerClient.fetchPassengersWithAircrafts()).thenReturn(mockPassengers);

        List<String> report = app.runPassengerAircraftQuery();

        assertEquals(3, report.size());
        assertTrue(report.get(0).contains("Mary"));
        assertTrue(report.get(0).contains("Aircrafts:"));
        assertFalse(report.get(0).contains("Airports:"));
    }

    @Test
    public void testRunPassengerWithAirportsQuery() throws Exception {
        List<Passenger> mockPassengers = List.of(
                TestConstants.MARY_W_AIRPORTS,
                TestConstants.JAMES_W_AIRPORTS,
                TestConstants.SOPHIA_W_AIRPORTS
        );
        Mockito.when(mockPassengerClient.fetchPassengersWithAirports()).thenReturn(mockPassengers);

        List<String> report = app.runPassengerAirportQuery();

        assertEquals(3, report.size());
        assertTrue(report.get(0).contains("Mary"));
        assertTrue(report.get(0).contains("Airports:"));
        assertFalse(report.get(0).contains("Aircrafts:"));
    }

}
