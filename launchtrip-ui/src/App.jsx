import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ItinerariesPage } from './components/Itineraries/ItinerariesPage'
import { ItineraryEditPage } from './components/Itineraries/ItineraryEditPage'
import { LocationsPage } from './components/Locations/LocationsPage'
import  TripPlanner  from './components/TripPlanner';
import LocationOfTheDay from './components/Sidebar/LocationOfTheDay';

//Added to support React Router
import ReactDOM from "react-dom/client";
import { Route, Routes } from "react-router-dom";
import { ItineraryReviewPage } from './components/Itineraries/ItineraryReviewPage'

//Previous code before adding React Router
/*
function App() {
  const [count, setCount] = useState(0)
  return (
    <>
    <ItinerariesPage></ItinerariesPage>
      <LocationsPage></LocationsPage>
      </>
    
  )
}

export default App
*/

//New code added to support React Router
function App() {
  return (
    <div className="app-container" style={{ display: 'flex' }}>
    <div className="sidebar" style={{ width: '300px', backgroundColor: '#f4f4f4', padding: '20px', position: 'fixed', right: '0', top: '50', height: '55%', boxShadow: '-2px 0 5px rgba(0,0,0,0.1)', overflowY: 'auto' }}>
      <TripPlanner />
    </div>
    <div className="main-content" style={{ flex: 1, padding: '20px', marginRight: '320px' }}>
    <div className="sidebar" style={{ width: '250px', backgroundColor: '#f4f4f4', padding: '10px' }}>
      <LocationOfTheDay />
    </div>
    <div className="main-content" style={{ flex: 1, padding: '10px' }}>
      <Routes>
      <Route path="/" element={<ItinerariesPage />} />
      <Route path="/LocationsPage" element={<LocationsPage />} />
      <Route path="/ItineraryEditPage/:itineraryId" element={<ItineraryEditPage />} />
      <Route path="/ItineraryReviewPage/:itineraryId" element={<ItineraryReviewPage />} />
      <Route path="/TripPlanner" element={<TripPlanner />} />
    </Routes>
    </div>
  </div>
 </div>
  );
}

export default App;
