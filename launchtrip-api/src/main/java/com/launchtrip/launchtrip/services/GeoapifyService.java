package com.launchtrip.launchtrip.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchtrip.launchtrip.models.Location;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeoapifyService {
    private static final String BASE_URL = "http://api.geoapify.com";
    private static final String API_KEY = "81e201745295492d891b0e474458e63c";

    public List<Location> getLocationsInCity() throws IOException {
        // ToDo: call Geocoding API to get the converted place ID
        String convertedPlaceIdFilter = "filter=place:515ec1c89203a357c0597c3bac72a18b4340f00101f901d026020000000000c0020692030b4b616e7361732043697479";

        // Start Building URL

        // Append v2/places
        String placesUrl = BASE_URL + "/v2/places";

        // Append Parameters

        // API Key
        placesUrl += "?apiKey=" + API_KEY;

        // Limit
        placesUrl += "&limit=10";

        // Place Types
        // ToDo: create a method for taking in a place type and converting it to a geoapify category
        placesUrl += "&categories=commercial.supermarket";

        // Filter Place
        placesUrl += "&" + convertedPlaceIdFilter;

        // Make the HTTP Call
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(placesUrl)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        List<Location> locations = new ArrayList<>();

        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                // Parse the response to a list of Location objects
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBodyString = responseBody.string();

                // Parse JSON response
                JsonNode root = objectMapper.readTree(responseBodyString);
                JsonNode features = root.path("features");
                for (JsonNode feature : features) {
                    JsonNode properties = feature.path("properties");
                    String name = properties.path("name").asText();
                    String usState = properties.path("state").asText();
                    String country = properties.path("country").asText();
                    String postcode = properties.path("postcode").asText();

                    // Extract categories as list
                    List<String> categories = new ArrayList<>();
                    for (JsonNode category : properties.path("categories")) {
                        categories.add(category.asText());
                    }

                    Location location = new Location(name, usState, country, postcode, categories);
                    locations.add(location);
                }
            }
        }

        return locations;
    }
}
