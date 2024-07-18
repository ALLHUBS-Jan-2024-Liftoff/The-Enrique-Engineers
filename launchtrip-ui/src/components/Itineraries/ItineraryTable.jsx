import React from "react";
import { ItineraryRowItem } from "./ItineraryRowItem";

export const ItineraryTable = ({ itineraries, deleteItinerary }) => {
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Description</th>
          <th scope="col">Assigned</th>
          <th scope="col">Actions</th>
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