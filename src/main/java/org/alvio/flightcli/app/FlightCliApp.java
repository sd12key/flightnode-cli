package org.alvio.flightcli.app;

import org.alvio.flightcli.client.AircraftClient;
import org.alvio.flightcli.client.CityClient;
import org.alvio.flightcli.client.PassengerClient;
import org.alvio.flightcli.domain.Aircraft;
import org.alvio.flightcli.domain.City;
import org.alvio.flightcli.domain.Passenger;
import org.alvio.flightcli.domain.Passenger.*;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

import static org.alvio.flightcli.util.AppUtil.generateStringReport;
import static org.alvio.flightcli.util.AppUtil.printQueryReport;


public class FlightCliApp {

    private final Scanner scanner = new Scanner(System.in);
    private CityClient cityClient;
    private AircraftClient aircraftClient;
    private PassengerClient passengerClient;

    public static void main(String[] args) {
        FlightCliApp app = new FlightCliApp();

        String baseUrl = args.length > 0 ? args[0] : "http://localhost:8080";
        HttpClient sharedHttpClient = HttpClient.newHttpClient();

        // city client setup
        CityClient cityClient = new CityClient();
        cityClient.setServerUrl(baseUrl);
        cityClient.setHttpClient(sharedHttpClient);
        app.setCityClient(cityClient);

        // aircraft client setup
        AircraftClient aircraftClient = new AircraftClient();
        aircraftClient.setServerUrl(baseUrl);
        aircraftClient.setHttpClient(sharedHttpClient);
        app.setAircraftClient(aircraftClient);

        // passenger client setup
        PassengerClient passengerClient = new PassengerClient();
        passengerClient.setServerUrl(baseUrl);
        passengerClient.setHttpClient(sharedHttpClient);
        app.setPassengerClient(passengerClient);

        app.runMenu();
    }

    public void setCityClient(CityClient cityClient) {
        this.cityClient = cityClient;
    }
    public void setAircraftClient(AircraftClient aircraftClient) {
        this.aircraftClient = aircraftClient;
    }
    public void setPassengerClient(PassengerClient passengerClient) { this.passengerClient = passengerClient; }

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
                case "2" -> {
                    System.out.println("\n--> GET /api/passengers?show-aircrafts=true\n");
                    queryReport = runPassengerAircraftQuery();
                }
                case "3" -> {
                    System.out.println("\n--> GET /api/aircrafts?show-airports=true\n");
                    queryReport = runAircraftAirportQuery();
                }
                case "4" -> {
                    System.out.println("\n--> GET /api/passengers?show-airports=true\n");
                    queryReport = runPassengerAirportQuery();
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

    public List<String> runAircraftAirportQuery() {
        try {
            List<Aircraft> aircraftList = aircraftClient.fetchAircraftsWithAirports();
            return generateStringReport(aircraftList, Aircraft::toDetailedString);
        } catch (Exception e) {
            String msg = "Error retrieving aircrafts: " + e.getClass().getSimpleName();
            if (e.getMessage() != null) msg += " - " + e.getMessage();
            return List.of(msg);
        }
    }

    public List<String> runPassengerAircraftQuery() {
        try {
            List<Passenger> passengers = passengerClient.fetchPassengersWithAircrafts();
            return generateStringReport(passengers, p -> p.toDetailedString(PassengerDetailMode.AIRCRAFTS_ONLY));
        } catch (Exception e) {
            String msg = "Error retrieving passengers (aircrafts): " + e.getClass().getSimpleName();
            if (e.getMessage() != null) msg += " - " + e.getMessage();
            return List.of(msg);
        }
    }

    public List<String> runPassengerAirportQuery() {
        try {
            List<Passenger> passengers = passengerClient.fetchPassengersWithAirports();
            return generateStringReport(passengers, p -> p.toDetailedString(PassengerDetailMode.AIRPORTS_ONLY));
        } catch (Exception e) {
            String msg = "Error retrieving passengers (airports): " + e.getClass().getSimpleName();
            if (e.getMessage() != null) msg += " - " + e.getMessage();
            return List.of(msg);
        }
    }


}
