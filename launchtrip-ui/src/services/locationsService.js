import axios from "axios";

const BASEAPIURL = "http://localhost:8080";

// export const fetchLocations = async () => {
//   try {
//     const response = await axios.get(`${BASEAPIURL}/api/locations`);
//     return response.data;
//   } catch (error) {
//     console.error("There was an error fetching the locations!", error);
//     throw error;
//   }
// };

export const searchLocations = (searchQuery) => {
  return axios.get(`${BASEAPIURL}/api/locations/downloadLocationsFromGeoapify`, { params: { searchQuery: searchQuery } })
    .then(response => response.data)
    .catch(error => {
      throw error;
    });
};

// export const createLocation = async (name, address) => {
//   try {
//     const response = await axios.post(`${BASEAPIURL}/api/locations/new`, null, {
//       params: { name, address },
//     });
//     return response.data;
//   } catch (error) {
//     console.error("There was an error creating the location!", error);
//     throw error;
//   }
// };

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

export const markLocationAsVisited = async (locationId) => {
  try {
    await axios.post(`${BASEAPIURL}/api/locations/toggleVisited`, null, {
      params: { locationId },
    });
    console.log("No error when toggling visited");
  } catch (error) {
    console.error("There was an error when toggling visited!", error);
    throw error;
  }
};