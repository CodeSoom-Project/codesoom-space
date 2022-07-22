import './App.css';
import Header from './components/Header';
import ReservationFormContainer from './ReservationFormContainer';
import ReservationListContainer from './ReservationListContainer';

function App() {
  return (
    <div>
      <Header/>
      <ReservationFormContainer/>
      <ReservationListContainer/>
    </div>
  );
}

export default App;
