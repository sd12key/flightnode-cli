package org.alvio.flightcli.domain;

import java.util.List;
import java.util.Objects;

public class Passenger {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Airport> airports;
    private List<Aircraft> aircrafts;

    public Passenger() {
    }

    public Passenger(Long id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Passenger(Long id, String firstName, String lastName, String phoneNumber,
                     List<Airport> airports, List<Aircraft> aircrafts) {
        this(id, firstName, lastName, phoneNumber);
        this.airports = airports;
        this.aircrafts = aircrafts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public enum PassengerDetailMode {
        NONE,
        AIRPORTS_ONLY,
        AIRCRAFTS_ONLY,
        BOTH
    }

    public String toDetailedString() {
        return toDetailedString(PassengerDetailMode.NONE);
    }

    @Override
    public String toString() {
        return "[id:" + id + "] " + firstName + " " + lastName + " (" + phoneNumber + ")";
    }

    public String toDetailedString(PassengerDetailMode mode) {
        String result = toString();

        if (mode == PassengerDetailMode.AIRPORTS_ONLY || mode == PassengerDetailMode.BOTH) {
            result += "\n   Airports:";
            if (airports != null && !airports.isEmpty()) {
                for (Airport airport : airports) {
                    result += "\n    - " + airport.toString();
                }
            } else {
                result += " None.";
            }
        }

        if (mode == PassengerDetailMode.AIRCRAFTS_ONLY || mode == PassengerDetailMode.BOTH) {
            result += "\n   Aircrafts:";
            if (aircrafts != null && !aircrafts.isEmpty()) {
                for (Aircraft aircraft : aircrafts) {
                    result += "\n    - " + aircraft.toString();
                }
            } else {
                result += " None.";
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger that = (Passenger) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(airports, that.airports) &&
                Objects.equals(aircrafts, that.aircrafts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, airports, aircrafts);
    }
}
