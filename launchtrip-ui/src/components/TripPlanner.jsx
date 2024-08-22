import React, { useState } from 'react';
import { searchLocations } from '../services/locationsService';

function TripPlanner() {
  const [city, setCity] = useState('');
  const [tripPlan, setTripPlan] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleCityChange = (e) => {
    setCity(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const allCityLocations = await searchLocations(city, '');
      const selectedLocations = filterAndSelectLocations(allCityLocations, [
        'restaurants',
        'natural',
        'entertainment',
        'accommodation',
        'tourism'
      ]);
      setTripPlan(selectedLocations);
      setLoading(false);
    } catch (error) {
      console.error('Error generating trip plan:', error);
      setError('Failed to generate trip plan');
      setLoading(false);
    }
  };

  const filterAndSelectLocations = (allCityLocations, selectedCategories) => {
    const filteredLocations = [];
    for (const category of selectedCategories) {
      const locationsInCategory = allCityLocations.filter(location =>
        location.categories.includes(category)
      );
      if (locationsInCategory.length > 0) {
        const randomLocation = locationsInCategory[Math.floor(Math.random() * locationsInCategory.length)];
        filteredLocations.push(randomLocation);
      }
    }
    return filteredLocations;
  };

  return (
    <div className="trip-planner">
      <h2>Plan Your Trip</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="city">Enter City Name:</label>
        <input
          type="text"
          id="city"
          value={city}
          onChange={handleCityChange}
          required
        />
        <button type="submit">Generate Trip Plan</button>
      </form>

      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}

      {tripPlan && (
        <div className="trip-plan">
          <h3>Trip Plan for {city}</h3>
          <ul>
            {tripPlan.map((location, index) => (
              <li key={index}>{location.name} ({location.category})</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default TripPlanner;
