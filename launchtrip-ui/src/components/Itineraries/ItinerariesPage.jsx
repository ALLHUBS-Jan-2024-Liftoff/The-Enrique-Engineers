import React, { useState, useEffect } from "react";
import { fetchItineraries, addItinerary, deleteItinerary } from "../../services/itineraryService";
import { ItineraryTable } from "./ItineraryTable";
import { NewItineraryForm } from "./NewItineraryForm";

export const ItinerariesPage = () => {
  const [showAddForm, setShowAddForm] = useState(false);
  const [itineraries, setItineraries] = useState([]);

  useEffect(() => {
    // Fetch all todos when the component mounts
    fetchItineraries()
      .then(setItineraries)
      .catch((error) => {
        console.error("There was an error fetching the itineraries!", error);
      });
  }, []);

  const handleAddItinerary = (name, visited) => {
    addItinerary(name, visited)
      .then((newItinerary) => {
        setItineraries([...itineraries, newItinerary]);
      })
      .catch((error) => {
        console.error("There was an error creating the itinerary!", error);
      });
  };

  const handleDeleteItinerary = (itineraryId) => {
    deleteItinerary(itineraryId)
      .then(() => {
        setItineraries(itineraries.filter((itinerary) => itinerary.id !== itineraryId));
      })
      .catch((error) => {
        console.error("There was an error deleting the itinerary!", error);
      });
  };

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">Your Itineraries</div>
        <div className="card-body">
          <ItineraryTable itineraries={itineraries} deleteItinerary={handleDeleteItinerary} />
          <button
            onClick={() => setShowAddForm(!showAddForm)}
            className="btn btn-primary"
          >
            {showAddForm ? "Close Form" : "New Itinerary"}
          </button>
          {showAddForm && <NewItineraryForm addItinerary={handleAddItinerary} />}
        </div>
      </div>
    </div>
  );
};