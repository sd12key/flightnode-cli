package org.alvio.flightcli.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.alvio.flightcli.domain.City;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CityClient {
    private String serverUrl;
    private HttpClient httpClient;

    public List<City> fetchCities() throws Exception {
        return fetchFromEndpoint("/api/cities");
    }

    public List<City> fetchCitiesWithAirports() throws Exception {
        return fetchFromEndpoint("/api/cities?show-airports=true");
    }

    protected List<City> fetchFromEndpoint(String path) throws Exception {
        String fullUrl = serverUrl + path;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed with status: " + response.statusCode());
        }

        return buildCityListFromResponse(response.body());
    }

    public List<City> buildCityListFromResponse(String responseBody) throws IOException {
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
