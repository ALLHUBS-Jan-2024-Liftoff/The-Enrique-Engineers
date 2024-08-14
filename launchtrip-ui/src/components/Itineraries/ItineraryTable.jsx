import React from "react";
import { ItineraryRowItem } from "./ItineraryRowItem";

export const ItineraryTable = ({ itineraries, deleteItinerary }) => {
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">Id #</th>
          <th scope="col">Itinerary Name</th>
          <th scope="col">Visited</th>
          <th scope="col">Edit Itinerary</th>
          <th scope="col">Review Itinerary</th>
          <th scope="col">Delete Itinerary</th>
        </tr>
      </thead>
      <tbody>
        {itineraries.map((itinerary) => (
          <ItineraryRowItem key={itinerary.id} itinerary={itinerary} deleteItinerary={deleteItinerary} />
        ))}
      </tbody>
    </table>
  );
};