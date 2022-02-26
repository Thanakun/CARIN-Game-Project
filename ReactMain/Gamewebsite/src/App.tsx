import './App.css';
import { BrowserRouter as  Router,Routes,Route, useNavigate, Link } from 'react-router-dom';
import {useState, useEffect} from "react"

// Store
import { MapStore } from './Store/MapStore'
// Store method
import { getMap } from './Store/MapStore'

// components
import PositionMap from './Components/PositionMap'
import Manu from './Components/Manu'
import Pause from './Components/Pause'
import End from './Components/End'

// images
import test from './Images/test.jpg'

function App() {
  // Store
  const state = MapStore.useState()

  // AllreactHook
  useEffect(() => {   
    getMap(Xaxis, Yaxis)
  }, [])


  // variables
  const [Xaxis, setXaxis] = useState<number>(20)
  const [Yaxis, setYaxis] = useState<number>(10)

  // functions


  return (
    <Router>
      <Routes>
        <Route path="/" element={<Manu/>}/>
        <Route path="/gameplay" element={<PositionMap x={Xaxis} y={Yaxis} map={state.map} maxScale={state.max_scale}/>}/>
        <Route path="/pause" element={<Pause/>}/>
        <Route path="/end" element={<End/>}/>
      </Routes>
      {/* <img src={test}></img> */}
    </Router>
  );
}

export default App