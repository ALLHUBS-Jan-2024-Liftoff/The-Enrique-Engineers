import React from "react";
import { markLocationAsVisited } from "../../services/locationsService";

export const LocationsRowItem = ({ location, deleteLocation }) => {
  return (
    <tr key={location.id}>
      <th scope="row">{location.id}</th>
      <td>{location.name}</td>
      <td>{location.usState}</td>
      <td>{location.country}</td>
      <td>{location.postcode}</td>
      <td>{location.categories.join(", ")}</td>
      <td>
        <button className="btn btn-danger" onClick={() => markLocationAsVisited(location.id)}>
        {location.visited ? 'visited' : 'unseen'}
        </button>
      </td>
    </tr>
  );
};