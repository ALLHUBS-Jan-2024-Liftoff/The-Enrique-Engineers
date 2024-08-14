import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import {
  getItineraryName,
} from "../../services/itineraryService";


export const ItineraryReviewPage = () => {
  const { itineraryId } = useParams();
  const [itineraryName, setItineraryName] = useState([]);

  useEffect(() => {
    getItineraryName(itineraryId)
      .then(setItineraryName)
      .catch((error) => {
        console.error("There was an error fetching the itinerary name!", error);
      });
  });

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">Review Itinerary</div>
        <div className="card-body">
          <h1>{itineraryName}</h1>
        </div>
        <div className="card-body">
          {/* add link to ReviewsPage */}
        </div>
      </div>
    </div>
  );
};
