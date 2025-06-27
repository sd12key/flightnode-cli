package org.alvio.flightcli.domain;

import java.util.List;
import java.util.Objects;

public class City {
    private long id;
    private String name;
    private String state;
    private int population;
    private List<Airport> airports;

    public City() {
    }

    public City(long id, String name, String state, int population) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.population = population;
    }

    public City(long id, String name, String state, int population, List<Airport> airports) {
        this(id, name, state, population);
        this.airports = airports;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    @Override
    public String toString() {
        return "[id:" + id + "] " + name + ", " + state + " (Pop: " + population + ")";
    }

    public String toDetailedString() {
        String result = toString();

        if (airports != null && !airports.isEmpty()) {
            result += "\n  Airports:";
            for (Airport airport : airports) {
                result += "\n    - " + airport.toString();
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return id == city.id &&
                population == city.population &&
                Objects.equals(name, city.name) &&
                Objects.equals(state, city.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state, population);
    }
}
