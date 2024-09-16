import React from 'react';
import { ItineraryTable } from './ItineraryTable'; // Adjust the import as necessary

const VisitedItineraries = ({ itineraries, deleteItinerary }) => {
  // Filter visited itineraries
  const visitedItineraries = itineraries.filter(itinerary => itinerary.visited);

  return (
    <div>
      <h3>Visited Itineraries</h3>
      {visitedItineraries.length > 0 ? (
        <ItineraryTable
          itineraries={visitedItineraries}
          deleteItinerary={deleteItinerary}
        />
      ) : (
        <p>No visited itineraries yet.</p>
      )}
    </div>
  );
};

export default VisitedItineraries;
