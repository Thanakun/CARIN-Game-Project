import React from 'react';
import {useState, useEffect} from "react"
import axios, {AxiosResponse} from "axios" ;
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useHistory
} from "react-router-dom";

// Store
import { MapStore } from './Store/MapStore'
// Store method
import { getMap } from './Store/MapStore'

// components
import PositionMap from './Components/PositionMap'
import Manu from './Components/Manu'
import Logo from './Components/Logo'
import Pause from './Components/Pause'

// images
import test from './Images/test.jpg'
import greenBox from '../Images/greenBox.png'
import redBox from '../Images/redBox.png'

// CSS
import './App.css';

function App() {
  // Store
  const state = MapStore.useState()

  // AllreactHook
  const history = useHistory()
  useEffect(() => {
    getMap(Xaxis, Yaxis)
    console.log(5678)
  }, [])


  // variables
  const [Xaxis, setXaxis] = useState<number>(40)
  const [Yaxis, setYaxis] = useState<number>(20)

  // functions
  function handleClick (path : string) {
    history.push(path)
  }

  return (
    <div>
      <Switch>

        <Route path="/" exact>
          {/* <img src={test}></img> */}
          <Logo/>
          <Manu funct={handleClick} path={'/gameplay'}/>
        </Route>

        <Route path="/gameplay">
          {/* {console.log("state : " + state.map)} */}
          <PositionMap x = {Xaxis} y = {Yaxis} funct1={handleClick} path={'/pause'} map={state.map}/>
        </Route>

        <Route path="/pause">
          <p>W8</p>
          <Pause funct={handleClick} path={'/gameplay'}/>
        </Route>

        <Route path="/endgame">
          <p>YOU WIN</p>
        </Route>

      </Switch>
    </div>
  );
}

export default App;
