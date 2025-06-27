package org.alvio.flightcli.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alvio.flightcli.domain.Passenger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PassengerClient {
    private String serverUrl;
    private HttpClient httpClient;

    public List<Passenger> fetchPassengers() throws Exception {
        return fetchFromEndpoint("/api/passengers");
    }

    public List<Passenger> fetchPassengersWithAirports() throws Exception {
        return fetchFromEndpoint("/api/passengers?show-airports=true");
    }

    public List<Passenger> fetchPassengersWithAircrafts() throws Exception {
        return fetchFromEndpoint("/api/passengers?show-aircrafts=true");
    }

    protected List<Passenger> fetchFromEndpoint(String path) throws Exception {
        String fullUrl = serverUrl + path;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed with status: " + response.statusCode());
        }

        return buildPassengerListFromResponse(response.body());
    }

    public List<Passenger> buildPassengerListFromResponse(String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(responseBody, new TypeReference<>() {});
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
