package org.alvio.flightcli.util;

import org.alvio.flightcli.domain.Aircraft;
import org.alvio.flightcli.domain.Airport;
import org.alvio.flightcli.domain.City;
import org.alvio.flightcli.domain.Passenger;

import java.util.Collections;
import java.util.List;

public class TestConstants {
    // Cities base
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
    public static final Airport DAL = new Airport(10, "Dallas Love Field", "DAL");
    public static final Airport DEN = new Airport(11, "Denver International Airport", "DEN");
    public static final Airport SEA = new Airport(12, "Seattle-Tacoma International Airport", "SEA");
    public static final Airport MIA = new Airport(14, "Miami International Airport", "MIA");
    public static final Airport PHX = new Airport(16, "Phoenix Sky Harbor International Airport", "PHX");
    public static final Airport BOS = new Airport(18, "Logan International Airport", "BOS");
    public static final Airport ATL = new Airport(19, "Hartsfield-Jackson Atlanta International Airport", "ATL");

    // Cities with airports (reuse NY, LA, MC)
    public static final City NY_W_AIR = new City(NY.getId(), NY.getName(), NY.getState(), NY.getPopulation(),
            List.of(JFK, LGA, EWR));
    public static final City LA_W_AIR = new City(LA.getId(), LA.getName(), LA.getState(), LA.getPopulation(),
            List.of(LAX, BUR, LGB));
    public static final City MC_W_AIR = new City(MC.getId(), MC.getName(), MC.getState(), MC.getPopulation(),
            Collections.emptyList());

    // Aircrafts
    public static final Aircraft UA_737 = new Aircraft(1, "United Airlines", 166, "Boeing 737-800");
    public static final Aircraft UA_757 = new Aircraft(2, "United Airlines", 169, "Boeing 757-200");
    public static final Aircraft UA_787 = new Aircraft(3, "United Airlines", 257, "Boeing 787-9 Dreamliner");
    public static final Aircraft DL_A321 = new Aircraft(6, "Delta Air Lines", 192, "Airbus A321");
    public static final Aircraft DL_763 = new Aircraft(7, "Delta Air Lines", 218, "Boeing 767-300ER");
    public static final Aircraft AA_737 = new Aircraft(9, "American Airlines", 160, "Boeing 737-800");
    public static final Aircraft AA_A321NEO = new Aircraft(11, "American Airlines", 196, "Airbus A321neo");
    public static final Aircraft AA_787 = new Aircraft(12, "American Airlines", 234, "Boeing 787-8 Dreamliner");

    // Aircrafts with airports
    public static final Aircraft UA_737_W_AIR = new Aircraft(UA_737.getId(), UA_737.getAirlineName(), UA_737.getCapacity(), UA_737.getType(),
            List.of(ORD), List.of(LAX));
    public static final Aircraft UA_757_W_AIR = new Aircraft(UA_757.getId(), UA_757.getAirlineName(), UA_757.getCapacity(), UA_757.getType(),
            List.of(ORD, SEA), List.of(LAX, ORD));
    public static final Aircraft UA_787_W_AIR = new Aircraft(UA_787.getId(), UA_787.getAirlineName(), UA_787.getCapacity(), UA_787.getType(),
            Collections.emptyList(), Collections.emptyList());

    // Passengers
    public static final Passenger MARY = new Passenger(1L, "Mary", "Flint", "5173331256");
    public static final Passenger JAMES = new Passenger(2L, "James", "Carter", "2125550198");
    public static final Passenger SOPHIA = new Passenger(3L, "Sophia", "Rodriguez", "3104445678");

    // Passengers with aircrafts
    public static final Passenger MARY_W_PLANES = new Passenger(MARY.getId(), MARY.getFirstName(), MARY.getLastName(), MARY.getPhoneNumber(),
            null, List.of(AA_737, AA_A321NEO, AA_787));
    public static final Passenger JAMES_W_PLANES = new Passenger(JAMES.getId(), JAMES.getFirstName(), JAMES.getLastName(), JAMES.getPhoneNumber(),
            null, List.of(UA_737, DL_763, AA_A321NEO));
    public static final Passenger SOPHIA_W_PLANES = new Passenger(SOPHIA.getId(), SOPHIA.getFirstName(), SOPHIA.getLastName(), SOPHIA.getPhoneNumber(),
            null, List.of(UA_757, DL_A321, AA_737, AA_A321NEO));

    // Passengers with airports
    public static final Passenger MARY_W_AIRPORTS = new Passenger(MARY.getId(), MARY.getFirstName(), MARY.getLastName(), MARY.getPhoneNumber(),
            List.of(JFK, SEA, PHX, BOS, ATL), null);
    public static final Passenger JAMES_W_AIRPORTS = new Passenger(JAMES.getId(), JAMES.getFirstName(), JAMES.getLastName(), JAMES.getPhoneNumber(),
            List.of(LAX, ORD, DEN, ATL), null);
    public static final Passenger SOPHIA_W_AIRPORTS = new Passenger(SOPHIA.getId(), SOPHIA.getFirstName(), SOPHIA.getLastName(), SOPHIA.getPhoneNumber(),
            List.of(LAX, ORD, DAL, MIA, PHX), null);

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

    public static final String PASSENGERS = """
        [
            {
                "id": 1,
                "firstName": "Mary",
                "lastName": "Flint",
                "phoneNumber": "5173331256"
            },
            {
                "id": 2,
                "firstName": "James",
                "lastName": "Carter",
                "phoneNumber": "2125550198"
            },
            {
                "id": 3,
                "firstName": "Sophia",
                "lastName": "Rodriguez",
                "phoneNumber": "3104445678"
            }
        ]
    """;

    public static final String PASSENGERS_WITH_AIRPORTS = """
        [
            {
                "id": 1,
                "firstName": "Mary",
                "lastName": "Flint",
                "phoneNumber": "5173331256",
                "airports": [
                    {
                        "id": 1,
                        "name": "John F. Kennedy International Airport",
                        "code": "JFK"
                    },
                    {
                        "id": 12,
                        "name": "Seattle-Tacoma International Airport",
                        "code": "SEA"
                    },
                    {
                        "id": 16,
                        "name": "Phoenix Sky Harbor International Airport",
                        "code": "PHX"
                    },
                    {
                        "id": 18,
                        "name": "Logan International Airport",
                        "code": "BOS"
                    },
                    {
                        "id": 19,
                        "name": "Hartsfield-Jackson Atlanta International Airport",
                        "code": "ATL"
                    }
                ]
            },
            {
                "id": 2,
                "firstName": "James",
                "lastName": "Carter",
                "phoneNumber": "2125550198",
                "airports": [
                    {
                        "id": 4,
                        "name": "Los Angeles International Airport",
                        "code": "LAX"
                    },
                    {
                        "id": 7,
                        "name": "O'Hare International Airport",
                        "code": "ORD"
                    },
                    {
                        "id": 11,
                        "name": "Denver International Airport",
                        "code": "DEN"
                    },
                    {
                        "id": 19,
                        "name": "Hartsfield-Jackson Atlanta International Airport",
                        "code": "ATL"
                    }
                ]
            },
            {
                "id": 3,
                "firstName": "Sophia",
                "lastName": "Rodriguez",
                "phoneNumber": "3104445678",
                "airports": [
                    {
                        "id": 4,
                        "name": "Los Angeles International Airport",
                        "code": "LAX"
                    },
                    {
                        "id": 7,
                        "name": "O'Hare International Airport",
                        "code": "ORD"
                    },
                    {
                        "id": 10,
                        "name": "Dallas Love Field",
                        "code": "DAL"
                    },
                    {
                        "id": 14,
                        "name": "Miami International Airport",
                        "code": "MIA"
                    },
                    {
                        "id": 16,
                        "name": "Phoenix Sky Harbor International Airport",
                        "code": "PHX"
                    }
                ]
            }
        ]
    """;

    public static final String PASSENGERS_WITH_AIRCRAFTS = """
        [
            {
                "id": 1,
                "firstName": "Mary",
                "lastName": "Flint",
                "phoneNumber": "5173331256",
                "aircrafts": [
                    {
                        "id": 9,
                        "type": "Boeing 737-800",
                        "airlineName": "American Airlines",
                        "capacity": 160
                    },
                    {
                        "id": 11,
                        "type": "Airbus A321neo",
                        "airlineName": "American Airlines",
                        "capacity": 196
                    },
                    {
                        "id": 12,
                        "type": "Boeing 787-8 Dreamliner",
                        "airlineName": "American Airlines",
                        "capacity": 234
                    }
                ]
            },
            {
                "id": 2,
                "firstName": "James",
                "lastName": "Carter",
                "phoneNumber": "2125550198",
                "aircrafts": [
                    {
                        "id": 1,
                        "type": "Boeing 737-800",
                        "airlineName": "United Airlines",
                        "capacity": 166
                    },
                    {
                        "id": 7,
                        "type": "Boeing 767-300ER",
                        "airlineName": "Delta Air Lines",
                        "capacity": 218
                    },
                    {
                        "id": 11,
                        "type": "Airbus A321neo",
                        "airlineName": "American Airlines",
                        "capacity": 196
                    }
                ]
            },
            {
                "id": 3,
                "firstName": "Sophia",
                "lastName": "Rodriguez",
                "phoneNumber": "3104445678",
                "aircrafts": [
                    {
                        "id": 2,
                        "type": "Boeing 757-200",
                        "airlineName": "United Airlines",
                        "capacity": 169
                    },
                    {
                        "id": 6,
                        "type": "Airbus A321",
                        "airlineName": "Delta Air Lines",
                        "capacity": 192
                    },
                    {
                        "id": 9,
                        "type": "Boeing 737-800",
                        "airlineName": "American Airlines",
                        "capacity": 160
                    },
                    {
                        "id": 11,
                        "type": "Airbus A321neo",
                        "airlineName": "American Airlines",
                        "capacity": 196
                    }
                ]
            }
        ]
    """;

}