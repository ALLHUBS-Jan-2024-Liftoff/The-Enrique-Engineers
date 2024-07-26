import React, { useState, useEffect } from "react";
import { fetchLocations, createLocation, deleteLocation } from "../../services/locationsService";
import { LocationsTable } from "./LocationsTable";
import { NewLocationForm } from "./NewLocationForm";

export const LocationsPage = () => {
  const [showAddForm, setShowAddForm] = useState(false);
  const [locations, setLocations] = useState([]);

  useEffect(() => {
    // Fetch all todos when the component mounts
    fetchLocations()
      .then(setLocations)
      .catch((error) => {
        console.error("There was an error fetching the locations!", error);
      });
  }, [locations]);

  const handleCreateLocation = (name, address) => {
    createLocation(name, address)
      .then((newLocation) => {
        setTodos([...locations, newLocation]);
      })
      .catch((error) => {
        console.error("There was an error creating the location!", error);
      });
  };

  const handleDeleteLocation = (locationId) => {
    deleteLocation(locationId)
      .then(() => {
        setLocations(locations.filter((location) => location.id !== locationId));
      })
      .catch((error) => {
        console.error("There was an error deleting the location!", error);
      });
  };

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">Your Locations</div>
        <div className="card-body">
          <LocationsTable locations={locations} deleteLocation={handleDeleteLocation} />
          <button
            onClick={() => setShowAddForm(!showAddForm)}
            className="btn btn-primary"
          >
            {showAddForm ? "Close Form" : "New Location"}
          </button>
          {showAddForm && <NewLocationForm createLocation={handleCreateLocation} />}
        </div>
      </div>
    </div>
  );
};