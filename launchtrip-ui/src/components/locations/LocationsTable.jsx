import React from "react";
import { LocationCard } from "./LocationCard";

export const LocationsTable = ({ locations, deleteLocation }) => {
  return (
    <div className="card-container">
      {locations.map((location) => (
        <LocationCard key={location.id} location={location} deleteLocation={deleteLocation} />
      ))}
    </div>
  );
};

