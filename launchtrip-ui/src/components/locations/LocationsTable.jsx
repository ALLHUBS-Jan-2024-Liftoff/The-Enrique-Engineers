import React from "react";
import { LocationsRowItem } from "./LocationsRowItem";

export const LocationsTable = ({ locations, deleteLocation }) => {
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">Id #</th>
          <th scope="col">Place Name</th>
          <th scope="col">Address</th>
        </tr>
      </thead>
      <tbody>
        {locations.map((location) => (
          <LocationsRowItem key={location.id} location={location} deleteLocation={deleteLocation} />
        ))}
      </tbody>
    </table>
  );
};