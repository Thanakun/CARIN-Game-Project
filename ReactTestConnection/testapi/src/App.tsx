
import './App.css';
import { BrowserRouter as  Router,Routes,Route } from 'react-router-dom';
import MainMenu from './components/MainMenu';
import Playing from './components/Playing';
import Pause from './components/Pause';
import End from './components/End';

function App() {
  return (
   <Router>
     <Routes>
      <Route path='/' element={<MainMenu/>}>
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
