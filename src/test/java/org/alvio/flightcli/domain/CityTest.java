package org.alvio.flightcli.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    @Test
    public void testCityFieldsAndToString() {
        City city = new City(1L, "New York", "NY", 8804190);
        assertEquals(1L, city.getId());
        assertEquals("New York", city.getName());
        assertEquals("NY", city.getState());
        assertEquals(8804190, city.getPopulation());
        assertNull(city.getAirports());
        assertEquals("New York, NY (Pop: 8804190)", city.toString());
    }

    @Test
    public void testCityEquality() {
        City a = new City(1L, "New York", "NY", 8804190);
        City b = new City(1L, "New York", "NY", 8804190);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testCityInequality() {
        City a = new City(1L, "New York", "NY", 8804190);
        City b = new City(2L, "New York", "NY", 8804190);
        assertNotEquals(a, b);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testCityWithAirportsAndDetailedString() {
        Airport jfk = new Airport(1L, "JFK Airport", "JFK");
        Airport lga = new Airport(2L, "LaGuardia", "LGA");

        List<Airport> airportList = new ArrayList<>();
        airportList.add(jfk);
        airportList.add(lga);

        City city = new City(1L, "New York", "NY", 8804190, airportList);
        assertEquals(2, city.getAirports().size());
        assertTrue(city.getAirports().contains(jfk));

        String detailed = city.toDetailedString();
        assertTrue(detailed.contains("New York, NY (Pop: 8804190)"));
        assertTrue(detailed.contains("JFK Airport (JFK)"));
        assertTrue(detailed.contains("LaGuardia (LGA)"));
    }
}
