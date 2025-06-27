package org.alvio.flightcli.app;

import org.alvio.flightcli.client.CityClient;
import org.alvio.flightcli.domain.City;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

import static org.alvio.flightcli.util.AppUtil.generateStringReport;
import static org.alvio.flightcli.util.AppUtil.printQueryReport;


public class FlightCliApp {

    private final Scanner scanner = new Scanner(System.in);
    private CityClient cityClient;

    public static void main(String[] args) {
        FlightCliApp app = new FlightCliApp();

        String baseUrl = args.length > 0 ? args[0] : "http://localhost:8080";
        HttpClient sharedHttpClient = HttpClient.newHttpClient();

        CityClient cityClient = new CityClient();
        cityClient.setServerUrl(baseUrl);
        cityClient.setHttpClient(sharedHttpClient);

        app.setCityClient(cityClient);
        app.runMenu();
    }

    public void setCityClient(CityClient cityClient) {
        this.cityClient = cityClient;
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n=== FLIGHT NODE REST CLIENT ===");

            System.out.println("   (1) What airports are there in each city?");
            System.out.println("   (2) What aircraft has each passenger flown on?");
            System.out.println("   (3) What airports do aircraft take off from and land at?");
            System.out.println("   (4) What airports have passengers used?");
            System.out.println("   (0) Exit");
            System.out.print("--> Enter your choice: ");

            String choice = scanner.nextLine();
            List<String> queryReport;

            switch (choice) {
                case "1" -> {
                    System.out.println("\n--> GET /api/cities?show-airports=true\n");
                    queryReport = runCityAirportQuery();
                }
                case "0" -> {
                    System.out.println("=========== Goodbye! ==========\n");
                    return;
                }
                default -> queryReport = List.of("<!> Unsupported option.");
            }

            printQueryReport(queryReport);
        }
    }

    public List<String> runCityAirportQuery() {
        try {
            List<City> cities = cityClient.fetchCitiesWithAirports();
            return generateStringReport(cities, City::toDetailedString);
        } catch (Exception e) {
            String msg = "Error retrieving cities: " + e.getClass().getSimpleName();
            if (e.getMessage() != null) msg += " - " + e.getMessage();
            return List.of(msg);
        }
    }
}
