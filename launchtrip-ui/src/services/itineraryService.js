import axios from "axios";

const BASEAPIURL = "http://localhost:8080";

export const fetchItineraries = async () => {
  try {
    const response = await axios.get(`${BASEAPIURL}/api/itineraries`);
    return response.data;
  } catch (error) {
    console.error("There was an error fetching the itineraries!", error);
    throw error;
  }
};

export const addItinerary = async (name, visited) => {
  try {
    const response = await axios.post(`${BASEAPIURL}/api/itineraries/new`, null, {
      params: { name, visited },
    });
    return response.data;
  } catch (error) {
    console.error("There was an error creating the itinerary!", error);
    throw error;
  }
};

export const deleteItinerary = async (itineraryId) => {
  try {
    await axios.post(`${BASEAPIURL}/api/itineraries/delete`, null, {
      params: { itineraryId },
    });
  } catch (error) {
    console.error("There was an error deleting the itinerary!", error);
    throw error;
  }
};

export const addLocationToItinerary = async (itineraryId, locationId) => {
  try {
      const response = await axios.post(`${BASEAPIURL}/api/itineraries/edit`, null, {
        params: { itineraryId, locationId },
      });
      return response.data;
    } catch (error) {
      console.error("There was an error adding location to itinerary!", error);
      throw error;
    }
}