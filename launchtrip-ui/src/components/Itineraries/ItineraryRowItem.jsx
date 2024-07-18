import React from "react";

export const ItineraryRowItem = ({ itinerary, deleteItinerary }) => {
  return (
    <tr key={todo.id}>
      <th scope="row">{todo.id}</th>
      <td>{todo.description}</td>
      <td>{todo.assigned}</td>
      <td>
        <button className="btn btn-danger" onClick={() => deleteItinerary(itinerary.id)}>
          Delete
        </button>
      </td>
    </tr>
  );
};