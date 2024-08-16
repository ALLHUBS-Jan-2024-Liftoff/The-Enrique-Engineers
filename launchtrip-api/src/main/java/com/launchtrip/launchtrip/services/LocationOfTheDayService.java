package com.launchtrip.launchtrip.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchtrip.launchtrip.models.Location;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LocationOfTheDayService {

    private static final String BASE_URL = "http://api.geoapify.com/v2/places";
    private static final String API_KEY = "81e201745295492d891b0e474458e63c";

    @Autowired
    private OkHttpClient httpClient;

    public List<Location> getLocationOfTheDay() {
        try {
            String randomCity = fetchRandomCity(); // Select a random city
            return fetchPlacesForCity(randomCity); // Fetch places for that city
        } catch (Exception e) {
            e.printStackTrace();
            return getDefaultLocations(); // Fallback in case of failure
        }
    }

    private String fetchRandomCity() throws IOException {
        String url = "https://api.geoapify.com/v1/geocode/search?text=city&format=json&apiKey=" + API_KEY;

        Request cityRequest = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();

        try (Response cityResponse = httpClient.newCall(cityRequest).execute()) {
            List<String> cities = new ArrayList<>();

            if (cityResponse.isSuccessful()) {
                ResponseBody cityBody = cityResponse.body();
                if (cityBody != null) {
                    ObjectMapper cityMapper = new ObjectMapper();
                    String cityString = cityBody.string();

                    JsonNode root = cityMapper.readTree(cityString);
                    JsonNode features = root.path("features");

                    for (JsonNode feature : features) {
                        JsonNode properties = feature.path("properties");
                        String city = properties.path("city").asText();
                        if (!city.isEmpty()) {
                            cities.add(city);
                        }
                    }
                }
            }

            if (!cities.isEmpty()) {
                Random random = new Random();
                return cities.get(random.nextInt(cities.size()));
            } else {
                throw new IOException("No cities found from GeoApify API");
            }
        }
    }

    private List<Location> fetchPlacesForCity(String city) throws IOException {
        List<Location> locations = new ArrayList<>();
        String[] categories = {"catering.restaurant", "natural", "entertainment", "accommodation.hotel", "tourism"};

        for (String category : categories) {
            String url = BASE_URL + "?categories=" + category + "&filter=place:" + city + "&limit=1&apiKey=" + API_KEY;
            Request request = new Request.Builder().url(url).build();
            Response response = httpClient.newCall(request).execute();
            List<Location> places = processApiResponse(response);

            if (!places.isEmpty()) {
                locations.add(places.get(0));
            }
        }

        return locations;
    }

    private List<Location> processApiResponse(Response response) throws IOException {
        List<Location> locations = new ArrayList<>();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBodyString = responseBody.string();

                JsonNode root = objectMapper.readTree(responseBodyString);
                JsonNode features = root.path("features");

                for (JsonNode feature : features) {
                    JsonNode properties = feature.path("properties");
                    String name = properties.path("name").asText();
                    String city = properties.path("city").asText();
                    String address = properties.path("formatted").asText();

                    // Assuming you have a constructor that fits this
                    Location location = new Location(name, city, null, null, null, null, null);
                    locations.add(location);
                }
            }
        }
        return locations;
    }

    private List<Location> getDefaultLocations() {
        List<Location> defaultLocations = new ArrayList<>();

        List<String> restaurantCategory = new ArrayList<>();
        restaurantCategory.add("catering.restaurant");

        List<String> naturalCategory = new ArrayList<>();
        naturalCategory.add("natural");

        List<String> entertainmentCategory = new ArrayList<>();
        entertainmentCategory.add("entertainment");

        List<String> hotelCategory = new ArrayList<>();
        hotelCategory.add("accommodation.hotel");

        List<String> tourismCategory = new ArrayList<>();
        tourismCategory.add("tourism");

        defaultLocations.add(new Location("Default Restaurant", "Default City", "default-place-id-1", "Default State", "Default Country", "00000", restaurantCategory));
        defaultLocations.add(new Location("Default Natural Place", "Default City", "default-place-id-2", "Default State", "Default Country", "00000", naturalCategory));
        defaultLocations.add(new Location("Default Entertainment", "Default City", "default-place-id-3", "Default State", "Default Country", "00000", entertainmentCategory));
        defaultLocations.add(new Location("Default Hotel", "Default City", "default-place-id-4", "Default State", "Default Country", "00000", hotelCategory));
        defaultLocations.add(new Location("Default Tourism Place", "Default City", "default-place-id-5", "Default State", "Default Country", "00000", tourismCategory));

        return defaultLocations;
    }
}
