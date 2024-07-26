import React from "react";

export const LocationsRowItem = ({ location, deleteLocation }) => {
  return (
    <tr key={location.id}>
      <th scope="row">{location.id}</th>
      <td>{location.name}</td>
      <td>{location.address}</td>
      <td>
        <button className="btn btn-danger" onClick={() => deleteLocation(location.id)}>
          Delete
        </button>
      </td>
    </tr>
  );
};