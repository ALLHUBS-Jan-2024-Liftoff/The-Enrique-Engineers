import React from "react";
import { LocationsRowItem } from "./LocationsRowItem";
import { downloadLocationsFromGeoapify } from "../../services/locationsService";

export const LocationsTable = ({ locations, deleteLocation }) => {
  return (
    <table className="table table-hover">
      <thead>
        <tr>
          <th scope="col">Id #</th>
          <th scope="col">Place Name</th>
          <th scope="col">State</th>
          <th scope="col">Country</th>
          <th scope="col">Postcode</th>
          <th scope="col">Categories</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody>
        {locations.map((location) => (
          <LocationsRowItem key={location.id} location={location} deleteLocation={deleteLocation} />
        ))}
        <button onClick={downloadLocationsFromGeoapify}>Download locations from GeoApify Service</button>
      </tbody>
    </table>
  );
};