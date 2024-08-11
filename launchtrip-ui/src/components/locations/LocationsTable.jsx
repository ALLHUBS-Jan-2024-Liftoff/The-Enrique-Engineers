import { React, useEffect } from "react";
import { useParams } from "react-router-dom";
import { LocationCard } from "./LocationCard";
import {
  addLocationToItinerary,
  getAddedLocationsFromItinerary,
} from "../../services/itineraryService";

export const LocationsTable = ({ locations, deleteLocation }) => {
  const { itineraryId } = useParams();
  useEffect(() => {
    /*
    let addedLocations = getAddedLocationsFromItinerary(itineraryId);

    for (const searchLocation of locations) {
      for (const addedLocation of addedLocations) {
        if (searchLocation.id == addedLocation.id) {
          console.log(searchLocation.name + " is already in the itinerary");
        }
      }
    }
*/
  });

  return (
    <div className="card-container">
      {locations.map((location) => (
        <div className="card-body">
          <button className="btn btn-primary" onClick={() => addLocationToItinerary(itineraryId, location.id)}>Add To Itinerary</button>
          <LocationCard key={location.id} location={location} deleteLocation={deleteLocation} />
        </div>
      ))}
    </div>
  );
};
