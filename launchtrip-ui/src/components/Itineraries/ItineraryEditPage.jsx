import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { LocationsPage } from "../Locations/LocationsPage";
import { LocationCard } from "../Locations/LocationCard";
import {
  getItineraryName,
  getAddedLocationsFromItinerary,
  removeLocationFromItinerary,
} from "../../services/itineraryService";
import { markLocationAsVisited } from "../../services/locationsService"

export const ItineraryEditPage = () => {
    const { itineraryId } = useParams();
    const [itineraryName, setItineraryName] = useState([]);
    const [addedLocations, setAddedLocations] = useState([]);
    const [changePage, setChangePage] = useState(0);

    useEffect(() => {
        getItineraryName(itineraryId)
          .then(setItineraryName)
          .catch((error) => {
            console.error("There was an error fetching the itinerary name!", error);
          });
    
        
          getAddedLocationsFromItinerary(itineraryId)
          .then(setAddedLocations)
          .catch((error) => {
            console.error(
              "There was an error fetching the added locations!",
              error
            );
          });
      }, [changePage]);

      const markLocationAsVisitedHandle = (itinId, locationId) => {

        markLocationAsVisited(itinId, locationId);
        setChangePage(changePage + 1);
      }

      const removeLocationFromItineraryHandle = (itinId, locationId) => {

        removeLocationFromItinerary(itinId, locationId);
        setChangePage(changePage + 2);
      }


      return (
        <div className="mt-5 container">
          <div className="card">
            <div className="card-header">Edit Your Itinerary</div>
            <div className="card-body">
              <h1>{itineraryName}</h1>{
              addedLocations.map((location) => (
                <div className="card-body">
                  <LocationCard key={location.id} location={location} />
                  <button className="btn btn-danger" onClick={() => removeLocationFromItineraryHandle(itineraryId, location.id)}>Remove From Itinerary</button>
                  <button className="btn btn-danger" onClick={() => markLocationAsVisitedHandle(itineraryId, location.id)}>
                    {location.visited ? "Visited" : "Unseen" }
                  </button>
                </div>
              ))}
            </div>
            <div className="card-body">
              <LocationsPage />
            </div>
          </div>
        </div>
      );
}