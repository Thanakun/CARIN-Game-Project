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

// images
import test from './Images/test.jpg'
import greenBox from '../Images/greenBox.png'
import redBox from '../Images/redBox.png'

function App() {
  // Store
  const state = MapStore.useState()

  // AllreactHook
  useEffect(() => {
    getMap(Xaxis, Yaxis)
  }, [])


  // variables
  const [Xaxis, setXaxis] = useState<number>(40)
  const [Yaxis, setYaxis] = useState<number>(20)

  // functions


  return (
    <Router>
      <Routes>
        <Route path="/" element={<Manu/>}/>
        <Route path="/gameplay" element={<PositionMap x={Xaxis} y={Yaxis} map={state.map}/>}/>
        <Route path="/pause" element={<Pause/>}/>
        <Route path="/end"/>
      </Routes>
      {/* <img src={test}></img> */}
    </Router>
  );
}

export default App