import { toggleLocationPaid } from "../../services/locationsService";
import { useParams } from "react-router";
import { useState, useEffect, useReducer } from "react";

export const LocationCard = ({ location }) => {
  const {itineraryId} = useParams();
  const [locationPaid, setLocationPaid] = useState(location.paid);

  useEffect(() => {
    const getIsLocationPaid = () => {
      setLocationPaid(location.paid);
    }

    getIsLocationPaid();
  }, []);

  const changeLocationPaidHandle = (locationId) => {
    let confirmation = confirm("Are you sure you want to change the paid attribute? This will affect every itinerary!");
    if (confirmation) {
      toggleLocationPaid(locationId);
      setLocationPaid(!locationPaid);
    }
  }

  return (
    <div className="card">
      <div className="card-body">
        <h5 className="card-title">{location.name}</h5>
        <h6 className="card-subtitle mb-2 text-muted">ID: {location.id}</h6>
        <p className="card-text">
          <strong>Location:</strong> {location.city + ", " + location.usState} <br />
          <strong>Country:</strong> {location.country} <br />
          <strong>Postcode:</strong> {location.postcode} <br />
          <strong>Categories:</strong> {location.categories.join(", ")} <br />
          <button className="btn btn-danger" onClick={() => changeLocationPaidHandle(location.id)}>
            {locationPaid ? "Paid Attraction" : "Free Attraction"}
          </button>
          <br />
        </p>
      </div>
    </div>
  );
};

