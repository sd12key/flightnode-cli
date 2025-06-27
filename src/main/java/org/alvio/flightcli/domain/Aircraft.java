package org.alvio.flightcli.domain;

import java.util.List;
import java.util.Objects;

public class Aircraft {
    private int id;
    private String airlineName;
    private int capacity;
    private String type;
    private List<Airport> departureAirports;
    private List<Airport> arrivalAirports;

    public Aircraft() {}

    public Aircraft(int id, String airlineName, int capacity, String type) {
        this.id = id;
        this.airlineName = airlineName;
        this.capacity = capacity;
        this.type = type;
    }

    public Aircraft(int id, String airlineName, int capacity, String type,
                    List<Airport> departureAirports, List<Airport> arrivalAirports) {
        this(id, airlineName, capacity, type);
        this.departureAirports = departureAirports;
        this.arrivalAirports = arrivalAirports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Airport> getDepartureAirports() {
        return departureAirports;
    }

    public void setDepartureAirports(List<Airport> departureAirports) {
        this.departureAirports = departureAirports;
    }

    public List<Airport> getArrivalAirports() {
        return arrivalAirports;
    }

    public void setArrivalAirports(List<Airport> arrivalAirports) {
        this.arrivalAirports = arrivalAirports;
    }

    @Override
    public String toString() {
        return "[id:" + id +
                "] " + type + " (Cap." + capacity + ")";
    }

    public String toDetailedString() {
        String result = toString();

        result += "\n  --> Departures:";
        if (departureAirports != null && !departureAirports.isEmpty()) {
            for (Airport airport : departureAirports) {
                result += "\n    - " + airport.toString();
            }
        } else {
            result += " No airports assigned.";
        }

        result += "\n  <-- Arrivals:";
        if (arrivalAirports != null && !arrivalAirports.isEmpty()) {
            for (Airport airport : arrivalAirports) {
                result += "\n    - " + airport.toString();
            }
        } else {
            result += " No airports assigned.";
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aircraft)) return false;
        Aircraft aircraft = (Aircraft) o;
        return id == aircraft.id &&
                capacity == aircraft.capacity &&
                Objects.equals(airlineName, aircraft.airlineName) &&
                Objects.equals(type, aircraft.type) &&
                Objects.equals(departureAirports, aircraft.departureAirports) &&
                Objects.equals(arrivalAirports, aircraft.arrivalAirports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airlineName, capacity, type, departureAirports, arrivalAirports);
    }
}
