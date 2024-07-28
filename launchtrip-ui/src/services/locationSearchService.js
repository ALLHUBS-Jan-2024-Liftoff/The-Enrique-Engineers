import axios from "axios";

const BASEAPIURL = "http://localhost:8080";

export const fetchLocationSearch = async (cityName) => {
    try{
        const search = await axios.get(`${BASEAPIURL}/api/call`, null, {
            params: { cityName },
          });
        return search.data;
    } catch (error) {
            console.error("There was an error searching for locations!", error);
            throw error;
          }
    };