import React, { useState } from "react";

export const NewItineraryForm = ({ addItinerary }) => {
  const [visited, setVisited] = useState("");
  const [name, setName] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (name !== "" && visited !== "") {
      addItinerary(name, visited);
      setVisited("");
      setName("");
    }
  };

  return (
    <div className="mt-5">
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">
            Visited
            <input
              type="text"
              className="form-control"
              value={visited}
              onChange={(e) => setVisited(e.target.value)}
              required
            />
          </label>
        </div>
        <div className="mb-3">
          <label className="form-label">
            Name
            <textarea
              rows={3}
              className="form-control"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </label>
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Add Itinerary
        </button>
      </form>
    </div>
  );
};