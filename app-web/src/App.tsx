import './App.css';

import { useEffect } from 'react';

import { Routes, Route } from 'react-router-dom';

import PrivateRoute from './routes/PrivateRoute';

import { setAccessToken } from './redux/authSlice';

import { loadItem } from './services/stoage';

import { useAppDispatch } from './hooks';

import HeaderContainer from './HeaderContainer';

import NotFound from './NotFound';
import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import Reservations from './pages/Reservations';

export default function App() {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(setAccessToken(loadItem('accessToken')));
  }, []);

  return (
    <div>
      <HeaderContainer/>
      <Routes>
        <Route path="/"/>
        <Route path="signup" element={<SignUpContainer/>}/>
        <Route path="login" element={<LogInContainer/>}/>
        <Route path="reservations" element={
          <PrivateRoute>
            <Reservations/>
          </PrivateRoute>
        }/>
        <Route path="*" element={<NotFound/>}/>
      </Routes>
    </div>
  );
}
