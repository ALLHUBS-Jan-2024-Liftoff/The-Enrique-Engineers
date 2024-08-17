import React, { useEffect, useState } from 'react';
import axios from 'axios';

const LocationOfTheDay = () => {
    const [location, setLocation] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchLocation = () => {
        setLoading(true);
        axios.get('http://localhost:8080/api/location-of-the-day')
            .then(response => {
                setLocation(response.data);
                setLoading(false);
            })
            .catch(error => {
                setError('Failed to fetch location of the day');
                setLoading(false);
            });
    };

    useEffect(() => {
        fetchLocation();
    }, []);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="location-of-the-day">
            <h2>Location of the Day</h2>
            <p><strong>{location.name}</strong></p>
            <p>{location.address}</p>
            <button onClick={fetchLocation}>Refresh Location</button>
        </div>
    );
};

export default LocationOfTheDay;
