import React, { useState, useEffect } from "react";
import { fetchLocations , searchLocations} from "../../services/locationsService";
import { LocationsTable } from "./LocationsTable";
import { SearchBar } from "./SearchBar";
//import { NewLocationForm } from "./NewLocationForm";

export const LocationsPage = () => {
  // const [showAddForm, setShowAddForm] = useState(false);
  const [locations, setLocations] = useState([]);

  useEffect(() => {
    // Fetch all locations when the component mounts
    fetchLocations()
      .then(setLocations)
      .catch((error) => {
        console.error("There was an error fetching the locations!", error);
      });
  }, [locations]);

  const handleDeleteLocation = (locationId) => {
    deleteLocation(locationId)
      .then(() => {
        setLocations(locations.filter((location) => location.id !== locationId));
      })
      .catch((error) => {
        console.error("There was an error deleting the location!", error);
      });
  };

  const handleSearch = (searchQuery) => {
    searchLocations(searchQuery)
      .then(setLocations)
      .catch((error) => {
        console.error("There was an error searching the locations!", error);
      });
  };

return (
  <div className="mt-5 container">
    <div className="card">
      <div className="card-header">Your Locations</div>
      <div className="card-body">
        <SearchBar onSearch={handleSearch} />
      </div>
      <div className="card-body">
        <LocationsTable locations={locations} deleteLocation={handleDeleteLocation} />
      </div>
    </div>
  </div>
);
};