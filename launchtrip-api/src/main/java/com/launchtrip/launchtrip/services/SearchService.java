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
public class SearchService {

    //@Autowired
    //LocationRepository locationRepository;

    // Todo: Separate out into three separate functions (searchLocationsFromQuery, fetchPlaceId, and fetchLocations) to utilize each separately and reduce overlap

    private static final String BASE_URL = "http://api.geoapify.com";
    private static final String API_KEY = "81e201745295492d891b0e474458e63c";

    public List<Location> searchLocationsFromQuery(String searchQuery) throws IOException {
        // Call Geocoding API to convert search query into PlaceId

        System.out.println("searchLocationsFromQuery() has been called");
        System.out.println("Search query: " + searchQuery);

        if (searchQuery.contains(" ")) {
            searchQuery.replace(" ", "%20");
        }


        OkHttpClient geocodingClient = new OkHttpClient().newBuilder()
                .build();
        Request geocodingRequest = new Request.Builder()
                .url("https://api.geoapify.com/v1/geocode/search?text=" + searchQuery + "&format=json&apiKey=" + API_KEY)
                .method("GET", null)
                .build();
        Response geocodingResponse = geocodingClient.newCall(geocodingRequest).execute();

        // Pull PlaceId from geocodingResponse

        // Testing: for now if placeId doesn't work it will default to KC
        String placeId = "51411da04500a557c05942959a3dd08c4340f00101f901d026020000000000c00208";

            if (geocodingResponse.isSuccessful()) {
                ResponseBody geocodingBody = geocodingResponse.body();
                if (geocodingBody != null) {
                    // Look through response to get placeId of first result
                    ObjectMapper geocodingMapper = new ObjectMapper();
                    String geocodingString = geocodingBody.string();
                    // Pull placeId
                    JsonNode root = geocodingMapper.readTree(geocodingString);
                    JsonNode results = root.path("results");
                    if (results.isArray() && results.size() > 0) {
                        JsonNode firstResult = results.get(0);
                        placeId = firstResult.path("place_id").asText();
                    }
                }
            }

        // Start Building URL

        // Append v2/places
        String placesUrl = BASE_URL + "/v2/places";

        // Append Parameters

        // API Key
        placesUrl += "?apiKey=" + API_KEY;

        // Limit
        placesUrl += "&limit=20";

        // Place Types
        // NOTE: This is searching all possible categories to put into the database. The selected search categories are filtered in the ---- call.
        placesUrl += "&categories=entertainment,natural,accommodation,tourism,catering.restaurant,catering.cafe,catering.bar,catering.taproom";

        // Filter Place
        placesUrl += "&filter=place:" + placeId;

            // Make the HTTP Call
            OkHttpClient placesClient = new OkHttpClient().newBuilder()
                    .build();
            Request placesRequest = new Request.Builder()
                    .url(placesUrl)
                    .method("GET", null)
                    .build();
            Response placesResponse = placesClient.newCall(placesRequest).execute();

            // Convert placesResponse JSON to an array list of Locations

            List<Location> locations = new ArrayList<>();

            if (placesResponse.isSuccessful()) {
                ResponseBody responseBody = placesResponse.body();
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
                        String city = properties.path("city").asText();
                        String usState = properties.path("state").asText();
                        String country = properties.path("country").asText();
                        String postcode = properties.path("postcode").asText();

                        // Extract categories as list
                        List<String> categories = new ArrayList<>();
                        for (JsonNode category : properties.path("categories")) {
                            categories.add(category.asText());
                        }

                        //Code Used For Debugging Purposes
                        /*
                        System.out.println("Name: " + name);
                        System.out.println("City: " + city);
                        System.out.println("Number of Categories: " + categories.stream().count());
                        System.out.println();
                        */

                        Location location = new Location(name, city, usState, country, postcode, categories);
                        /*Added by Brett. Check to see if the location has a 'no_fee.no' attribute*/
                        if (categories.contains("no_fee.no")) {
                            location.setPaid(false);
                        }

                        locations.add(location);
                    }
                }
            }
            return locations;
        }
    }