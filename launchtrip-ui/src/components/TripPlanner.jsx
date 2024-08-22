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
        'catering',
        // 'natural', need to update category name - not yielding results as is
        'entertainment',
        // 'accommodation', need to update category name - not yielding results as is
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
    const selectedLocationNames = new Set();

    selectedCategories.forEach((category) => {
      const locationsInCategory = allCityLocations.filter(location =>
        location.categories.some(apiCategory => apiCategory.includes(category))
      );

      const availableLocations = locationsInCategory.filter(
        location => !selectedLocationNames.has(location.name)
      );
      
      if (availableLocations.length > 0) {
        const randomLocation = availableLocations[Math.floor(Math.random() * availableLocations.length)];
        filteredLocations.push(randomLocation);
        selectedLocationNames.add(randomLocation.name); // Mark location as selected to reduce category dupes
      }
    });
    
    return filteredLocations;
  };

  return (
    <div className="trip-planner">
      <h2>Know where to go, but not what to do?</h2>
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
              <li key={index}>{location.name}</li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default TripPlanner;
