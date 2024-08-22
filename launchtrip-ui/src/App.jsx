import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ItinerariesPage } from './components/Itineraries/ItinerariesPage'
import { ItineraryEditPage } from './components/Itineraries/ItineraryEditPage'
import { LocationsPage } from './components/Locations/LocationsPage'
import LoginForm from './components/signupForm/signupForm'
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
    <Routes>
      <Route path="/" element={<ItinerariesPage />} />
      <Route path="/LocationsPage" element={<LocationsPage />} />
      <Route path="/ItineraryEditPage/:itineraryId" element={<ItineraryEditPage />} />
      <Route path="/ItineraryReviewPage/:itineraryId" element={<ItineraryReviewPage />} />
    </Routes>
  )
}

export default App;
