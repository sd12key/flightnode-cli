package org.alvio.flightcli.util;

import org.alvio.flightcli.domain.Aircraft;
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

    public static final Airport ORD = new Airport(7, "O'Hare International Airport", "ORD");
    public static final Airport SEA = new Airport(12, "Seattle-Tacoma International Airport", "SEA");


    // Cities with airports
    public static final City NY_W_AIR = new City(1, "New York", "NY", 8804190,
            List.of(JFK, LGA, EWR));
    public static final City LA_W_AIR = new City(2, "Los Angeles", "CA", 3898747,
            List.of(LAX, BUR, LGB));
    public static final City MC_W_AIR = new City(11, "Macon", "GA", 157346,
            Collections.emptyList());

    // Aircrafts
    public static final Aircraft UA_737 = new Aircraft(1, "United Airlines", 166, "Boeing 737-800");
    public static final Aircraft UA_757 = new Aircraft(2, "United Airlines", 169, "Boeing 757-200");
    public static final Aircraft UA_787 = new Aircraft(3, "United Airlines", 257, "Boeing 787-9 Dreamliner");

    // Aircrafts with airports
    public static final Aircraft UA_737_W_AIR = new Aircraft(1, "United Airlines", 166, "Boeing 737-800",
            List.of(ORD), List.of(LAX));
    public static final Aircraft UA_757_W_AIR = new Aircraft(2, "United Airlines", 169, "Boeing 757-200",
            List.of(ORD, SEA), List.of(LAX, ORD));
    public static final Aircraft UA_787_W_AIR = new Aircraft(3, "United Airlines", 257, "Boeing 787-9 Dreamliner",
            Collections.emptyList(), Collections.emptyList());

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

    public static final String AIRCRAFTS = """
            [
                 {
                     "id": 1,
                     "airlineName": "United Airlines",
                     "capacity": 166,
                     "type": "Boeing 737-800"
                 },
                 {
                     "id": 2,
                     "airlineName": "United Airlines",
                     "capacity": 169,
                     "type": "Boeing 757-200"
                 },
                 {
                     "id": 3,
                     "airlineName": "United Airlines",
                     "capacity": 257,
                     "type": "Boeing 787-9 Dreamliner"
                 }
            ]
            
            """;

    public static final String AIRCRAFTS_WITH_AIRPORTS = """
            [
                  {
                      "id": 1,
                      "airlineName": "United Airlines",
                      "capacity": 166,
                      "type": "Boeing 737-800",
                      "departureAirports": [
                          {
                              "id": 7,
                              "name": "O'Hare International Airport",
                              "code": "ORD"
                          }
                      ],
                      "arrivalAirports": [
                          {
                              "id": 4,
                              "name": "Los Angeles International Airport",
                              "code": "LAX"
                          }
                      ]
                  },
                  {
                      "id": 2,
                      "airlineName": "United Airlines",
                      "capacity": 169,
                      "type": "Boeing 757-200",
                      "departureAirports": [
                          {
                              "id": 7,
                              "name": "O'Hare International Airport",
                              "code": "ORD"
                          },
                          {
                              "id": 12,
                              "name": "Seattle-Tacoma International Airport",
                              "code": "SEA"
                          }
                      ],
                      "arrivalAirports": [
                          {
                              "id": 4,
                              "name": "Los Angeles International Airport",
                              "code": "LAX"
                          },
                          {
                              "id": 7,
                              "name": "O'Hare International Airport",
                              "code": "ORD"
                          }
                      ]
                  },
                  {
                      "id": 3,
                      "airlineName": "United Airlines",
                      "capacity": 257,
                      "type": "Boeing 787-9 Dreamliner",
                      "departureAirports": [],
                      "arrivalAirports": []
                  }
            ]
            """;
}