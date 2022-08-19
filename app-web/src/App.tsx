import './App.css';
import { useDispatch } from 'react-redux';
import { setAccessToken } from './authSlice';
import SeatList from './seatList';
import { loadItem } from './services/stoage';
import HeaderContainer from './HeaderContainer';

export default function App() {
  const dispatch = useDispatch();

  const accessToken = loadItem('accessToken');
  if (accessToken) {
    dispatch(setAccessToken(accessToken));
  }


  return (
    <div>
      <HeaderContainer/>
      <SeatList/>
      {/*<ReservationFormContainer/>*/}
      {/*<ReservationListContainer/>*/}
    </div>
  );
}
