import React from "react";
import { ItineraryEditPage } from "./ItineraryEditPage";


//New code to support React Router
import { Route, Routes, Link } from "react-router-dom"



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
      <td>
        <button className="btn btn-primary"><Link to={"/ItineraryEditPage/" + itinerary.id} target="_blank">Edit</Link></button>
      </td>
    </tr>
  );
};