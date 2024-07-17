import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ItinerariesPage } from './components/Itineraries/ItinerariesPage'

function App() {
  const [count, setCount] = useState(0)

// start coding frontend here - to pull from itineraries.jsx

  return (
    <>
      <ItinerariesPage></ItinerariesPage>
    </>
  )
}

export default App
