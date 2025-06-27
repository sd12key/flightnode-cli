package org.alvio.flightcli.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @Test
    public void testAirportFieldsAndToString() {
        Airport airport = new Airport(1L, "JFK Airport", "JFK");

        assertEquals(1L, airport.getId());
        assertEquals("JFK Airport", airport.getName());
        assertEquals("JFK", airport.getCode());
        assertEquals("[id:1] JFK Airport (JFK)", airport.toString());
    }

    @Test
    public void testAirportEquality() {
        Airport a1 = new Airport(1L, "JFK Airport", "JFK");
        Airport a2 = new Airport(1L, "JFK Airport", "JFK");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    public void testAirportInequality() {
        Airport a1 = new Airport(1L, "JFK Airport", "JFK");
        Airport a2 = new Airport(2L, "LaGuardia", "LGA");

        assertNotEquals(a1, a2);
        assertNotEquals(a1.hashCode(), a2.hashCode());
    }
}
