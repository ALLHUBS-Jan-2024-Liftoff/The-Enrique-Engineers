import React, { useState } from "react";

export const NewLocationForm = ({ createLocation }) => {
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (name !== "" && address !== "") {
      createLocation(name, address);
      setName("");
      setAddress("");
    }
  };

  return (
    <div className="mt-5">
      <form onSubmit={handleSubmit}>
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
        <div className="mb-3">
          <label className="form-label">
            Address
            <input
              type="text"
              className="form-control"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
              required
            />
          </label>
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Create Location
        </button>
      </form>
    </div>
  );
};
