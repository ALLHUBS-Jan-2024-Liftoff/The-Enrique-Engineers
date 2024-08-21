import React from "react";
import { ItineraryEditPage } from "./ItineraryEditPage";


//New code to support React Router
import { Route, Routes, Link } from "react-router-dom"
import { markItineraryAsVisited } from "../../services/itineraryService";



export const ItineraryRowItem = ({ itinerary, deleteItinerary }) => {
  return (
    <tr key={itinerary.id}>
      <th scope="row">{itinerary.id}</th>
      <td>{itinerary.name}</td>
      <td>
        <button className="btn btn-danger" onClick={() => markItineraryAsVisited(itinerary.id)}>
        {itinerary.visited ? 'Visited' : 'Unseen'} </button>
      </td>
      <td>
        <button className="btn btn-primary"><Link to={"/ItineraryEditPage/" + itinerary.id} target="_blank">Edit</Link></button>
      </td>
      <td>
        <button className="btn btn-primary" disabled={!itinerary.visited}>
          {itinerary.visited ? (
          <Link to={"/ItineraryReviewPage/" + itinerary.id} target="_blank">
          Write a Review
          </Link>
          ) : (
            "Write a Review"
          )}
        </button>
      </td>
      <td>
        <button className="btn btn-danger" onClick={() => deleteItinerary(itinerary.id)}>
          Delete
        </button>
      </td>
    </tr>
  );
};