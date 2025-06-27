package org.alvio.flightcli.domain;

import java.util.Objects;

public class Airport {
    private long id;
    private String name;
    private String code;

    public Airport() {
    }

    public Airport(long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return id == airport.id &&
                Objects.equals(name, airport.name) &&
                Objects.equals(code, airport.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
