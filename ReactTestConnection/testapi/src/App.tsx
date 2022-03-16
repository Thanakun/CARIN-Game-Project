
import './App.css';
import { BrowserRouter as  Router,Routes,Route } from 'react-router-dom';
import MainMenu from './Components/MainMenu';
import Playing from './Components/Playing';
import Pause from './Components/Pause';
import End from './Components/End';
import Setup from './Components/Setup';



function App() {
  return (
   <Router>
     <Routes>
      <Route path='/' element={<MainMenu/>}>
      </Route>
      <Route path='/setup' element={ <Setup/>}>
      </Route>
      <Route path='/gameplay' element={ <Playing/>}>
      </Route>

      <Route path='/pause' element={ <Pause/>}>
      </Route>

      <Route path='/end' element = { <End/>}>
      </Route>
      
     </Routes>
   </Router>
  );
}

export default App;
