package org.alvio.flightcli.client;

import org.alvio.flightcli.domain.Passenger;
import org.alvio.flightcli.util.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerClientTest {

    private PassengerClient passengerClient;
    private HttpClient mockHttpClient;
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setup() {
        passengerClient = new PassengerClient();
        passengerClient.setServerUrl("http://test");
        mockHttpClient = Mockito.mock(HttpClient.class);
        mockResponse = Mockito.mock(HttpResponse.class);
    }

    @Test
    public void testFetchPassengersWithAirports() throws Exception {
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn(TestConstants.PASSENGERS_WITH_AIRPORTS);
        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        passengerClient.setHttpClient(mockHttpClient);
        List<Passenger> passengers = passengerClient.fetchPassengersWithAirports();

        assertEquals(3, passengers.size());
        assertEquals(TestConstants.MARY_W_AIRPORTS, passengers.get(0));
        assertEquals(TestConstants.JAMES_W_AIRPORTS, passengers.get(1));
        assertEquals(TestConstants.SOPHIA_W_AIRPORTS, passengers.get(2));
    }

    @Test
    public void testFetchPassengersWithAircrafts() throws Exception {
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn(TestConstants.PASSENGERS_WITH_AIRCRAFTS);
        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        passengerClient.setHttpClient(mockHttpClient);
        List<Passenger> passengers = passengerClient.fetchPassengersWithAircrafts();

        assertEquals(3, passengers.size());
        assertEquals(TestConstants.MARY_W_PLANES, passengers.get(0));
        assertEquals(TestConstants.JAMES_W_PLANES, passengers.get(1));
        assertEquals(TestConstants.SOPHIA_W_PLANES, passengers.get(2));
    }

    @Test
    public void testBuildPassengerListFromResponseWithoutAirportsOrAircrafts() throws Exception {
        List<Passenger> passengers = passengerClient.buildPassengerListFromResponse(TestConstants.PASSENGERS);

        assertEquals(3, passengers.size());

        Passenger mary = passengers.get(0);
        assertEquals("Mary", mary.getFirstName());
        assertNull(mary.getAirports());
        assertNull(mary.getAircrafts());

        Passenger james = passengers.get(1);
        assertEquals("James", james.getFirstName());

        Passenger sophia = passengers.get(2);
        assertEquals("Sophia", sophia.getFirstName());
    }

    @Test
    public void testBuildPassengersFromResponseWithAirports() throws Exception {
        List<Passenger> passengers = passengerClient.buildPassengerListFromResponse(TestConstants.PASSENGERS_WITH_AIRPORTS);

        assertEquals(3, passengers.size());
        assertEquals(TestConstants.MARY_W_AIRPORTS, passengers.get(0));
        assertEquals(TestConstants.JAMES_W_AIRPORTS, passengers.get(1));
        assertEquals(TestConstants.SOPHIA_W_AIRPORTS, passengers.get(2));
    }

    @Test
    public void testBuildPassengersFromResponseWithAircrafts() throws Exception {
        List<Passenger> passengers = passengerClient.buildPassengerListFromResponse(TestConstants.PASSENGERS_WITH_AIRCRAFTS);

        assertEquals(3, passengers.size());
        assertEquals(TestConstants.MARY_W_PLANES, passengers.get(0));
        assertEquals(TestConstants.JAMES_W_PLANES, passengers.get(1));
        assertEquals(TestConstants.SOPHIA_W_PLANES, passengers.get(2));
    }
}
