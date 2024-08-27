import axios from "axios";

const BASEAPIURL = "http://localhost:8080";

export const searchLocations = (searchQuery, selectedCategories) => {
  return axios.get(`${BASEAPIURL}/api/locations/downloadLocationsFromGeoapify`, { 
    params: { 
      searchQuery: searchQuery, 
      categories: selectedCategories,
    } 
  })
    .then(response => response.data)
    .catch(error => {
      throw error;
    });
};

export const deleteLocation = async (locationId) => {
  try {
    await axios.post(`${BASEAPIURL}/api/locations/delete`, null, {
      params: { locationId },
    });
    console.log("No error when deleting location");
  } catch (error) {
    console.error("There was an error deleting the location!", error);
    throw error;
  }
};

export const downloadLocationsFromGeoapify = async () => {
  try {
    const response = await axios.get(`${BASEAPIURL}/api/locations/downloadLocationsFromGeoapify`);
    //console.log("Response: ", response.data)
    return response.data;
  } catch (error) {
    console.error("There was an error downloading locations from GeoApify!", error);
    throw error;
  }
};

export const fetchLocations = async () => {
  try {
    const response = await axios.get(`${BASEAPIURL}/api/locations/getStoredLocations`);
    //console.log("Response: ", response.data)
    return response.data;
  } catch (error) {
    console.error("There was an error fetching the locations!", error);
    throw error;
  }
};

export const markLocationAsVisited = async (itineraryId, locationId) => {
  try {
    console.log("itineraryId: " + itineraryId);
    console.log("locationId: " + locationId);
    await axios.post(`${BASEAPIURL}/api/locations/toggleVisited`, null, {
      params: { itineraryId, locationId },
    });
    console.log("No error when toggling visited");
  } catch (error) {
    console.error("There was an error when toggling visited!", error);
    throw error;
  }
};

export const getIsLocationVisited = async (itineraryId, locationId) => {
  let requestUrl = `${BASEAPIURL}/api/locations/getIsLocationVisited/${itineraryId}/${locationId}`;
  try {
    axios.get(requestUrl).then(function(response) 
    {
      console.log("getIsLocationVisited() function");
      console.log("itineraryId: ", itineraryId);
      console.log("locationId: ", locationId);
    });
  } catch (error) {
    console.log("Error: ", error);
    console.log("There was an error when retrieving isLocationVisited: ", itineraryId, locationId);
  }
  return false;
};

export const toggleLocationPaid = async (locationId) => {
  let requestUrl = `${BASEAPIURL}/api/locations/toggleLocationPaid`;
  try {
    console.log("toggleLocationPaid() is being called!");
    console.log(locationId + " paid property is being toggled");
    await axios.post(requestUrl, null, {params: { locationId}});
    console.log("No error when toggling paid");
  }
  catch (error) {
    console.error("There was an error when toggling paid!", error);
  }
}