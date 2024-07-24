import React from "react";

export const ItineraryRowItem = ({ itinerary, deleteItinerary }) => {
  return (
    <tr key={itinerary.id}>
      <th scope="row">{itinerary.id}</th>
      <td>{itinerary.name}</td>
      <td>{itinerary.visited? "Visited" : "Unseen"}</td>
      <td>
        <button className="btn btn-danger" onClick={() => deleteItinerary(itinerary.id)}>
          Delete
        </button>
      </td>
    </tr>
  );
};