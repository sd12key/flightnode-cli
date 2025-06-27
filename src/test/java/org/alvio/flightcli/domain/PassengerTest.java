package org.alvio.flightcli.domain;

import org.alvio.flightcli.util.TestConstants;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.alvio.flightcli.domain.Passenger.PassengerDetailMode.*;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    @Test
    public void testPassengerFieldsAndToString() {
        Passenger p = new Passenger(1L, "Mary", "Flint", "5173331256");
        assertEquals(1L, p.getId());
        assertEquals("Mary", p.getFirstName());
        assertEquals("Flint", p.getLastName());
        assertEquals("5173331256", p.getPhoneNumber());
        assertNull(p.getAirports());
        assertNull(p.getAircrafts());

        assertEquals("[id:1] Mary Flint (5173331256)", p.toString());
    }

    @Test
    public void testPassengerEquality() {
        Passenger a = new Passenger(1L, "Mary", "Flint", "5173331256",
                List.of(TestConstants.JFK), List.of(TestConstants.UA_737));
        Passenger b = new Passenger(1L, "Mary", "Flint", "5173331256",
                List.of(TestConstants.JFK), List.of(TestConstants.UA_737));
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testPassengerInequality() {
        Passenger a = new Passenger(1L, "Mary", "Flint", "5173331256",
                List.of(TestConstants.JFK), List.of(TestConstants.UA_737));
        Passenger b = new Passenger(2L, "Mary", "Flint", "5173331256",
                List.of(TestConstants.JFK), List.of(TestConstants.UA_737));
        assertNotEquals(a, b);
    }

    @Test
    public void testDetailedStringWithAirportsAndAircrafts() {
        Passenger p = new Passenger(1L, "Mary", "Flint", "5173331256",
                List.of(TestConstants.JFK, TestConstants.LAX),
                List.of(TestConstants.UA_737, TestConstants.AA_787));

        String detailed = p.toDetailedString(BOTH);
        assertTrue(detailed.contains("Mary Flint"));
        assertTrue(detailed.contains("JFK"));
        assertTrue(detailed.contains("LAX"));
        assertTrue(detailed.contains("Boeing 737-800"));
        assertTrue(detailed.contains("Boeing 787-8 Dreamliner"));
    }

    @Test
    public void testDetailedStringWithOnlyAircrafts() {
        Passenger p = new Passenger(1L, "Mary", "Flint", "5173331256",
                null, List.of(TestConstants.UA_737, TestConstants.AA_787));

        String detailed = p.toDetailedString(AIRCRAFTS_ONLY);

        assertTrue(detailed.contains("Mary Flint"));
        assertFalse(detailed.contains("Airports"));
        assertTrue(detailed.contains("Aircrafts:"));
        assertTrue(detailed.contains("Boeing 737-800"));
        assertTrue(detailed.contains("Boeing 787-8 Dreamliner"));
    }

    @Test
    public void testDetailedStringWithOnlyAirports() {
        Passenger p = new Passenger(1L, "Mary", "Flint", "5173331256",
                List.of(TestConstants.JFK, TestConstants.LAX), null);

        String detailed = p.toDetailedString(AIRPORTS_ONLY);

        assertTrue(detailed.contains("Mary Flint"));
        assertTrue(detailed.contains("Airports:"));
        assertTrue(detailed.contains("JFK"));
        assertTrue(detailed.contains("LAX"));
        assertFalse(detailed.contains("Aircrafts"));
    }


    @Test
    public void testDetailedStringWithNoAirportsOrAircrafts() {
        Passenger p = new Passenger(1L, "Mary", "Flint", "5173331256");
        String detailed = p.toDetailedString(NONE);

        assertFalse(detailed.contains("None."));
        assertFalse(detailed.contains("None."));
    }
}
