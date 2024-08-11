import React from "react";
import { markLocationAsVisited } from "../../services/locationsService";
import { addLocationToItinerary } from "../../services/itineraryService";
import { useParams } from "react-router";

export const LocationCard = ({ location }) => {
  
  const {itineraryId} = useParams();

  return (
    <div className="card">
      <div className="card-body">
        <h5 className="card-title">{location.name}</h5>
        <h6 className="card-subtitle mb-2 text-muted">ID: {location.id}</h6>
        <p className="card-text">
          <strong>Location:</strong> {location.city + ", " + location.usState} <br />
          <strong>Country:</strong> {location.country} <br />
          <strong>Postcode:</strong> {location.postcode} <br />
          <strong>Categories:</strong> {location.categories.join(", ")} <br />
          {/* Added a button so a location can be added to the itinerary  */}
          {/* <button className="btn btn-primary" onClick={() => addLocationToItinerary(itineraryId, location.id)}>Add</button> */}
          
          <button className="btn btn-danger" onClick={() => markLocationAsVisited(location.id)}>
          {location.visited ? 'Visited' : 'Unseen'} </button>
        </p>
      </div>
    </div>
  );
};

