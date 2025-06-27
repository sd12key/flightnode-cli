package org.alvio.flightcli.client;

import org.alvio.flightcli.domain.City;
import org.alvio.flightcli.util.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.alvio.flightcli.util.TestConstants.CITIES_SIMPLE;
import static org.alvio.flightcli.util.TestConstants.CITIES_WITH_AIRPORTS;
import static org.junit.jupiter.api.Assertions.*;

public class CityClientTest {

    private CityClient cityClient;

    private void TestCities (List<City> cities) {
        assertNotNull(cities);
        assertEquals(3, cities.size());

        City ny = cities.get(0);
        assertNotNull(ny);
        assertEquals(1, ny.getId());
        assertEquals("New York", ny.getName());
        assertEquals("NY", ny.getState());
        assertEquals(8804190, ny.getPopulation());
        assertNull(ny.getAirports());

        City la = cities.get(1);
        assertNotNull(la);
        assertEquals(2, la.getId());
        assertEquals("Los Angeles", la.getName());
        assertEquals("CA", la.getState());
        assertEquals(3898747, la.getPopulation());
        assertNull(la.getAirports());
    }


    @BeforeEach
    public void setup() {
        cityClient = new CityClient();
        cityClient.setServerUrl("http://test");
    }

    @Test
    public void testBuildCityListFromResponseWithoutAirports() throws Exception {
        List<City> cities = cityClient.buildCityListFromResponse(CITIES_SIMPLE);

        assertEquals(3, cities.size());

        City ny = cities.get(0);
        assertEquals("New York", ny.getName());
        assertEquals(8804190, ny.getPopulation());
        assertNull(ny.getAirports());

        City la = cities.get(1);
        assertEquals("Los Angeles", la.getName());
        assertEquals("CA", la.getState());
        assertNull(la.getAirports());
    }

    @Test
    public void testBuildCityListFromResponseWithAirports() throws Exception {
        List<City> cities = cityClient.buildCityListFromResponse(CITIES_WITH_AIRPORTS);

        assertEquals(3, cities.size());

        City ny = cities.get(0);
        assertEquals("New York", ny.getName());
        assertNotNull(ny.getAirports());
        assertEquals(3, ny.getAirports().size());
        assertEquals("JFK", ny.getAirports().get(0).getCode());

        City la = cities.get(1);
        assertEquals("Los Angeles", la.getName());
        assertEquals("LAX", la.getAirports().get(0).getCode());
    }

    @Test
    public void testFetchCitiesWithAirportsMockedHttp() throws Exception {
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn(TestConstants.CITIES_WITH_AIRPORTS);
        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        cityClient.setHttpClient(mockHttpClient);

        List<City> cities = cityClient.fetchCitiesWithAirports();

        assertEquals(3, cities.size());

        City ny = cities.get(0);
        assertEquals("New York", ny.getName());
        assertEquals("JFK", ny.getAirports().get(0).getCode());

        City ma = cities.get(2);


    }

    @Test
    public void testFetchCitiesWithoutAirportsMockedHttp() throws Exception {
        HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn(TestConstants.CITIES_SIMPLE);
        Mockito.when(mockHttpClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        cityClient.setHttpClient(mockHttpClient);

        List<City> cities = cityClient.fetchCitiesWithAirports();

        assertEquals(3, cities.size());

        City ny = cities.get(0);
        assertEquals("New York", ny.getName());

        City ma = cities.get(2);
        assertEquals("GA", ma.getState());
        assertNull(ma.getAirports());

    }


}
