import React, { useState, useEffect } from 'react';
import { fetchItineraries } from '../../services/itineraryService';
import { ItineraryTable } from './ItineraryTable'; 

const VisitedItinerariesPage = () => {
  const [itineraries, setItineraries] = useState([]);

  useEffect(() => {
    
    fetchItineraries()
      .then(setItineraries)
      .catch((error) => {
        console.error("There was an error fetching the itineraries!", error);
      });
  }, []);

  
  const visitedItineraries = itineraries.filter(itinerary => itinerary.visited);

  return (
    <div className="mt-5 container">
      <div className="card">
        <div className="card-header">
          Visited Itineraries
        </div>
        <div className="card-body">
          {visitedItineraries.length > 0 ? (
            <ItineraryTable
              itineraries={visitedItineraries}
              deleteItinerary={() => {}} 
            />
          ) : (
            <p>No visited itineraries yet.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default VisitedItinerariesPage;
