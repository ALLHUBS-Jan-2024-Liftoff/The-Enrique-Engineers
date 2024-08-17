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
    const response = await axios.post(
      `${BASEAPIURL}/api/itineraries/new`,
      null,
      {
        params: { name, visited },
      }
    );
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
    const response = await axios.post(
      `${BASEAPIURL}/api/itineraries/addLocationToItinerary`,
      null,
      {
        params: { itineraryId, locationId },
      }
    );
    return response.data;
  } catch (error) {
    console.error("There was an error adding location to itinerary!", error);
    throw error;
  }
};

export const removeLocationFromItinerary = async (itineraryId, locationId) => {
  try {
    const response = await axios.post(
      `${BASEAPIURL}/api/itineraries/removeLocationFromItinerary`,
      null,
      {
        params: { itineraryId, locationId },
      }
    );
    return response.data;
  } catch (error) {
    console.error("There was an error removing location from itinerary!", error);
    throw error;
  }
};

export const getAddedLocationsFromItinerary = async (itineraryId) => {
  let requestUrl = `${BASEAPIURL}/api/itineraries/getAddedLocations/${itineraryId}`;
  let addedLocations = [];
  try {
    await axios.get(requestUrl).then(function(response) {
      addedLocations = response.data;
    })
  } catch (error) {
    console.log("Error: ", error);
    console.log("There was an error when retrieving added locations from itinerary: ", itineraryId);
  }
  return addedLocations;
};

export const getItineraryName = async (itineraryId) => {
  let itineraryName = "";
  let requestUrl = `${BASEAPIURL}/api/itineraries/getItineraryName/${itineraryId}`;
  try {
    await axios.get(requestUrl).then(function(response) 
    {
      itineraryName = response.data;
      //console.log("itineraryName: ", itineraryName);
    });
  } catch (error) {
    console.log("Error: ", error);
    console.log("There was an error when retrieving details from itinerary: ", itineraryId);
  }
  return itineraryName;
}

export const markItineraryAsVisited = async (itineraryId) => {
  try {
    await axios.post(`${BASEAPIURL}/api/itineraries/toggleItineraryVisited`, null, {
      params: { itineraryId },
    });
    console.log("No error when toggling visited");
  } catch (error) {
    console.error("There was an error when toggling visited!", error);
    throw error;
  };
};

export const submitReview = async (itineraryId, review) => {
  try {
    const response = await axios.post(`${BASEAPIURL}/api/itineraries/${itineraryId}/setReviews`, review);
    return response.data; 
  } catch (error) {
    console.error("There was an error adding the review to this itinerary!", error);
    throw error;
  }
};

export const getReviewsForItinerary = async (itineraryId) => {
  try {
    const response = await axios.get(`${BASEAPIURL}/api/itineraries/${itineraryId}/getReviews`);
    return response.data;
  } catch (error) {
    console.error("There was an error getting the reviews!", error); 
    throw error;
  }
};
