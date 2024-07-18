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

export const addItinerary = async (description, assigned) => {
  try {
    const response = await axios.post(`${BASEAPIURL}/api/itineraries/new`, null, {
      params: { description, assigned },
    });
    return response.data;
  } catch (error) {
    console.error("There was an error creating the itinerary!", error);
    throw error;
  }
};

export const deleteItinerary = async (todoId) => {
  try {
    await axios.post(`${BASEAPIURL}/api/itineraries/delete`, null, {
      params: { todoId },
    });
  } catch (error) {
    console.error("There was an error deleting the itinerary!", error);
    throw error;
  }
};