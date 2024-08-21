// src/components/quiz/TripQuizPage.jsx
import React, { useState } from 'react';
import axios from 'axios';

const TripQuizPage = () => {
    const [cityName, setCityName] = useState('');
    const [tripPlan, setTripPlan] = useState(null);
    const [error, setError] = useState(null);

    const fetchTripPlan = () => {
        setError(null);
        axios.get(`http://localhost:8080/api/trip-plan?cityName=${cityName}`)
            .then(response => {
                setTripPlan(response.data);
            })
            .catch(err => {
                setError('Failed to generate trip plan');
            });
    };

    return (
        <div>
            <h2>Trip Planning Quiz</h2>
            <input 
                type="text" 
                value={cityName} 
                onChange={(e) => setCityName(e.target.value)} 
                placeholder="Enter city name"
            />
            <button onClick={fetchTripPlan}>
                Generate Trip Plan
            </button>

            {error && <p>{error}</p>}
            
            {tripPlan && (
                <div>
                    <h3>Trip Plan for {cityName}</h3>
                    <ul>
                        <li>Restaurant: {tripPlan.restaurant?.name || 'Not available'}</li>
                        <li>Natural: {tripPlan.natural?.name || 'Not available'}</li>
                        <li>Entertainment: {tripPlan.entertainment?.name || 'Not available'}</li>
                        <li>Accommodation: {tripPlan.accommodation?.name || 'Not available'}</li>
                        <li>Tourism: {tripPlan.tourism?.name || 'Not available'}</li>
                    </ul>
                </div>
            )}
        </div>
    );
};

export default TripQuizPage;
