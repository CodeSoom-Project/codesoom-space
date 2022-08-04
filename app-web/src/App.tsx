import './App.css';
import Header from './components/Header';
import ReservationFormContainer from './ReservationFormContainer';
import ReservationListContainer from './ReservationListContainer';
import {useDispatch} from "react-redux";
import {setAccessToken} from "./authSlice";
import SeatList from "./seatList";

function App() {
  const dispatch = useDispatch();

  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    dispatch(setAccessToken(accessToken));
  }


  return (
    <div>
      <Header/>
      <SeatList/>
      <ReservationFormContainer/>
      <ReservationListContainer/>
    </div>
  );
}

export default App;
