import React, { useState, useEffect } from "react";
import {
  fetchItineraries,
  addItinerary,
  deleteItinerary,
} from "../../services/itineraryService";
import { ItineraryTable } from "./ItineraryTable";
import { NewItineraryForm } from "./NewItineraryForm";
import Logout from "../logOut/LogOutAction"; // Adjust path as necessary
import { useNavigate } from 'react-router-dom';

export const ItinerariesPage = () => {
  const [showAddForm, setShowAddForm] = useState(false);
  const [itineraries, setItineraries] = useState([]);
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    // Fetch all itineraries when the component mounts
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
        setItineraries(
          itineraries.filter((itinerary) => itinerary.id !== itineraryId)
        );
      })
      .catch((error) => {
        console.error("There was an error deleting the itinerary!", error);
      });
  };

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header d-flex justify-content-between align-items-center">
          <div>
            Your Itineraries
          </div>
          <div>
            {/* Include the Logout and View Visited Itineraries buttons here */}
            <button
              onClick={() => navigate('/visited-itineraries')}
              className="btn btn-secondary me-2" // Adjust margin-right if needed
            >
              View Visited Itineraries
            </button>
            <Logout />
          </div>
        </div>
        <div className="card-body">
          {/* Display all itineraries */}
          <ItineraryTable
            itineraries={itineraries.filter(itinerary => !itinerary.visited)}
            deleteItinerary={handleDeleteItinerary}
          />
          <button
            onClick={() => setShowAddForm(!showAddForm)}
            className="btn btn-primary"
          >
            {showAddForm ? "Close Form" : "New Itinerary"}
          </button>
          {showAddForm && (
            <NewItineraryForm addItinerary={handleAddItinerary} />
          )}
        </div>
      </div>
    </div>
  );
};
