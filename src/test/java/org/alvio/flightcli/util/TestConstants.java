package org.alvio.flightcli.util;

import org.alvio.flightcli.domain.Airport;
import org.alvio.flightcli.domain.City;

import java.util.Collections;
import java.util.List;

public class TestConstants {
    // Cities simple
    public static final City NY = new City(1, "New York", "NY", 8804190, null);
    public static final City LA = new City(2, "Los Angeles", "CA", 3898747, null);
    public static final City MC = new City(11, "Macon", "GA", 157346, null);

    // Airports
    public static final Airport JFK = new Airport(1, "John F. Kennedy International Airport", "JFK");
    public static final Airport LGA = new Airport(2, "LaGuardia Airport", "LGA");
    public static final Airport EWR = new Airport(3, "Newark Liberty International Airport", "EWR");
    public static final Airport LAX = new Airport(4, "Los Angeles International Airport", "LAX");
    public static final Airport BUR = new Airport(5, "Hollywood Burbank Airport", "BUR");
    public static final Airport LGB = new Airport(6, "Long Beach Airport", "LGB");

    // Cities with airports
    public static final City NY_W_AIR = new City(1, "New York", "NY", 8804190,
            List.of(JFK, LGA, EWR));
    public static final City LA_W_AIR = new City(2, "Los Angeles", "CA", 3898747,
            List.of(LAX, BUR, LGB));
    public static final City MC_W_AIR = new City(11, "Macon", "GA", 157346,
            Collections.emptyList());

    // JSON strings
    public static final String CITIES_SIMPLE = """
            [
                {
                    "id": 1,
                    "name": "New York",
                    "state": "NY",
                    "population": 8804190
                },
                {
                    "id": 2,
                    "name": "Los Angeles",
                    "state": "CA",
                    "population": 3898747
                },
                {
                    "id": 11,
                    "name": "Macon",
                    "state": "GA",
                    "population": 157346
                }
            ]
            """;

    public static final String CITIES_WITH_AIRPORTS = """
            [
                {
                    "id": 1,
                    "name": "New York",
                    "state": "NY",
                    "population": 8804190,
                    "airports": [
                        {
                            "id": 1,
                            "name": "John F. Kennedy International Airport",
                            "code": "JFK"
                        },
                        {
                            "id": 2,
                            "name": "LaGuardia Airport",
                            "code": "LGA"
                        },
                        {
                            "id": 3,
                            "name": "Newark Liberty International Airport",
                            "code": "EWR"
                        }
                    ]
                },
                {
                    "id": 2,
                    "name": "Los Angeles",
                    "state": "CA",
                    "population": 3898747,
                    "airports": [
                        {
                            "id": 4,
                            "name": "Los Angeles International Airport",
                            "code": "LAX"
                        },
                        {
                            "id": 5,
                            "name": "Hollywood Burbank Airport",
                            "code": "BUR"
                        },
                        {
                            "id": 6,
                            "name": "Long Beach Airport",
                            "code": "LGB"
                        }
                    ]
                },
                {
                    "id": 11,
                    "name": "Macon",
                    "state": "GA",
                    "population": 157346,
                    "airports": []
                }
            ]
            """;
}