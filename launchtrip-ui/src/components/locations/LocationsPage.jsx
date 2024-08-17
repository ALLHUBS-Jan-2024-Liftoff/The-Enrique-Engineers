import React, { useState, useEffect } from "react";
import { searchLocations} from "../../services/locationsService";
import { LocationsTable } from "./LocationsTable";
import { SearchBar } from "./SearchBar";
//import { NewLocationForm } from "./NewLocationForm";

export const LocationsPage = () => {
  const [locations, setLocations] = useState([]);

  const handleDeleteLocation = (locationId) => {
    deleteLocation(locationId)
      .then(() => {
        setLocations(locations.filter((location) => location.id !== locationId));
      })
      .catch((error) => {
        console.error("There was an error deleting the location!", error);
      });
  };

  const handleSearch = (searchQuery, selectedCategories) => {
    searchLocations(searchQuery, selectedCategories)
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