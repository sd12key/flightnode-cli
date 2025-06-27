package org.alvio.flightcli.domain;

import org.alvio.flightcli.util.TestConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.alvio.flightcli.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class AircraftTest {

    @Test
    public void testAircraftFieldsAndToString() {
        Aircraft aircraft = UA_737;
        assertEquals(1, aircraft.getId());
        assertEquals("United Airlines", aircraft.getAirlineName());
        assertEquals(166, aircraft.getCapacity());
        assertEquals("Boeing 737-800", aircraft.getType());
        assertNull(aircraft.getDepartureAirports());
        assertNull(aircraft.getArrivalAirports());

        assertEquals("[id:1] Boeing 737-800 (Cap.166)", aircraft.toString());
    }

    @Test
    public void testAircraftEquality() {
        Aircraft a = new Aircraft(1, "United Airlines", 166, "Boeing 737-800");
        Aircraft b = new Aircraft(1, "United Airlines", 166, "Boeing 737-800");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testAircraftInequality() {
        Aircraft a = new Aircraft(1, "United Airlines", 166, "Boeing 737-800");
        Aircraft b = new Aircraft(2, "United Airlines", 169, "Boeing 757-200");
        assertNotEquals(a, b);
        assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testAircraftWithAirportsAndDetailedString() {
        Aircraft aircraft = UA_757_W_AIR;

        List<Airport> deps = aircraft.getDepartureAirports();
        assertNotNull(deps);
        assertEquals(2, deps.size());
        assertTrue(deps.contains(ORD));
        assertTrue(deps.contains(SEA));

        List<Airport> arrs = aircraft.getArrivalAirports();
        assertNotNull(arrs);
        assertEquals(2, arrs.size());
        assertTrue(arrs.contains(LAX));
        assertTrue(arrs.contains(ORD));

        String detailed = aircraft.toDetailedString();
        assertTrue(detailed.contains("Boeing 757-200"));
        assertTrue(detailed.contains("ORD"));
        assertTrue(detailed.contains("SEA"));
        assertTrue(detailed.contains("LAX"));
    }

    @Test
    public void testAircraftWithNoAirports() {
        Aircraft aircraft = UA_787_W_AIR;

        assertNotNull(aircraft.getDepartureAirports());
        assertTrue(aircraft.getDepartureAirports().isEmpty());

        assertNotNull(aircraft.getArrivalAirports());
        assertTrue(aircraft.getArrivalAirports().isEmpty());

        String detailed = aircraft.toDetailedString();
        assertTrue(detailed.contains("assigned"));
    }

}
