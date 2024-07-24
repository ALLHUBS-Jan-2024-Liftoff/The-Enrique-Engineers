import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ItinerariesPage } from './components/Itineraries/ItinerariesPage'
//import signupForm from './components/signupForm/signupForm';



function App() {
  const [count, setCount] = useState(0)

// start coding frontend here - to pull from "services" files

  return (
    <div>
      <ItinerariesPage />
    </div>
  )
}

export default App
