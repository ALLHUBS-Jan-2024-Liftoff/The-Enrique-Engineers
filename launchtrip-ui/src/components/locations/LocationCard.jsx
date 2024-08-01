import React from "react";
import { markLocationAsVisited } from "../../services/locationsService";

export const LocationCard = ({ location, deleteLocation }) => {
  return (
    <div className="card">
      <div className="card-body">
        <h5 className="card-title">{location.name}</h5>
        <h6 className="card-subtitle mb-2 text-muted">ID: {location.id}</h6>
        <p className="card-text">
          <strong>State:</strong> {location.usState} <br />
          <strong>Country:</strong> {location.country} <br />
          <strong>Postcode:</strong> {location.postcode} <br />
          <strong>Categories:</strong> {location.categories.join(", ")} <br />
          <button className="btn btn-danger" onClick={() => markLocationAsVisited(location.id)}>
          {location.visited ? 'Visited' : 'Unseen'} </button>
        </p>
      </div>
    </div>
  );
};

