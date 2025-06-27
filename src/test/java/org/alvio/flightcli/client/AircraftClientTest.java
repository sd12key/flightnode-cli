package org.alvio.flightcli.client;

import org.alvio.flightcli.domain.Aircraft;
import org.alvio.flightcli.util.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AircraftClientTest {

    private AircraftClient aircraftClient;
    private HttpClient mockHttpClient;
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setUp() {
        aircraftClient = new AircraftClient();
        aircraftClient.setServerUrl("http://test");
        mockHttpClient = Mockito.mock(HttpClient.class);
        mockResponse = Mockito.mock(HttpResponse.class);
    }

    @Test
    public void testBuildAircraftListFromResponseWithoutAirports() throws Exception {
        List<Aircraft> aircrafts = aircraftClient.buildAircraftListFromResponse(TestConstants.AIRCRAFTS);
        assertEquals(3, aircrafts.size());
        assertEquals(TestConstants.UA_737, aircrafts.get(0));
        assertEquals(TestConstants.UA_757, aircrafts.get(1));
        assertEquals(TestConstants.UA_787, aircrafts.get(2));
    }

    @Test
    public void testBuildAircraftListFromResponseWithAirports() throws Exception {
        List<Aircraft> aircrafts = aircraftClient.buildAircraftListFromResponse(TestConstants.AIRCRAFTS_WITH_AIRPORTS);
        assertEquals(3, aircrafts.size());

        Aircraft b737 = aircrafts.get(0);
        assertEquals("Boeing 737-800", b737.getType());
        assertEquals("ORD", b737.getDepartureAirports().get(0).getCode());
        assertEquals("LAX", b737.getArrivalAirports().get(0).getCode());

        Aircraft b757 = aircrafts.get(1);
        assertEquals(2, b757.getDepartureAirports().size());
        assertEquals(2, b757.getArrivalAirports().size());

        Aircraft b787 = aircrafts.get(2);
        assertTrue(b787.getDepartureAirports().isEmpty());
        assertTrue(b787.getArrivalAirports().isEmpty());
    }

    @Test
    public void testFetchAircrafts() throws Exception {
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn(TestConstants.AIRCRAFTS);

        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        aircraftClient.setHttpClient(mockHttpClient);

        List<Aircraft> aircrafts = aircraftClient.fetchAircrafts();

        assertEquals(3, aircrafts.size());
        assertEquals(TestConstants.UA_737, aircrafts.get(0));
        assertEquals(TestConstants.UA_757, aircrafts.get(1));
        assertEquals(TestConstants.UA_787, aircrafts.get(2));
    }

    @Test
    public void testFetchAircraftsWithAirports() throws Exception {
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn(TestConstants.AIRCRAFTS_WITH_AIRPORTS);

        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        aircraftClient.setHttpClient(mockHttpClient);

        List<Aircraft> aircrafts = aircraftClient.fetchAircraftsWithAirports();

        assertEquals(3, aircrafts.size());

        Aircraft b737 = aircrafts.get(0);
        assertEquals(TestConstants.UA_737.getType(), b737.getType());
        assertEquals(1, b737.getDepartureAirports().size());
        assertEquals(1, b737.getArrivalAirports().size());

        Aircraft b757 = aircrafts.get(1);
        assertEquals(TestConstants.UA_757.getType(), b757.getType());
        assertEquals(2, b757.getDepartureAirports().size());
        assertEquals(2, b757.getArrivalAirports().size());

        Aircraft b787 = aircrafts.get(2);
        assertEquals(TestConstants.UA_787.getType(), b787.getType());
        assertTrue(b787.getDepartureAirports().isEmpty());
        assertTrue(b787.getArrivalAirports().isEmpty());
    }
}
