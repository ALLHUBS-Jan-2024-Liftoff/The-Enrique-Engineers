import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ItinerariesPage } from './components/Itineraries/ItinerariesPage'
import { LocationsPage } from './components/locations/LocationsPage'

function App() {
  const [count, setCount] = useState(0)

// start coding frontend here - to pull from "services" files

  return (
    <>
      <ItinerariesPage></ItinerariesPage>
      <LocationsPage></LocationsPage>
    </>
  )
}

export default App
