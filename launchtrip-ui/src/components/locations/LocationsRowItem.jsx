import React from "react";

export const LocationsRowItem = ({ location, deleteLocation }) => {
  return (
    <tr key={location.id}>
      <th scope="row">{location.id}</th>
      <td>{location.name}</td>
      <td>{location.state}</td>
      <td>{location.country}</td>
      <td>{location.postcode}</td>
      <td>{location.categories.join(", ")}</td>
      <td>
        <button className="btn btn-danger" onClick={() => deleteLocation(location.id)}>
          Delete
        </button>
      </td>
    </tr>
  );
};