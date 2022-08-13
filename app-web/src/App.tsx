import './App.css';
import Header from './components/Header';
import ReservationFormContainer from './ReservationFormContainer';
import ReservationListContainer from './ReservationListContainer';
import { useDispatch } from 'react-redux';
import { setAccessToken } from './authSlice';
import SeatList from './seatList';
import { loadItem } from './services/stoage';

export default function App() {
  const dispatch = useDispatch();

  const accessToken = loadItem('accessToken');
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
