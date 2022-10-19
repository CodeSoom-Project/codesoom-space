import './App.css';

import { useEffect } from 'react';

import { useAppDispatch } from './hooks';

import { setAccessToken } from './authSlice';

import HeaderContainer from './HeaderContainer';

import { loadItem } from './services/stoage';

import { Routes, Route } from 'react-router-dom';

import NotFound from './NotFound';
import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import Reservations from './pages/Reservations';

export default function App() {
  const dispatch = useAppDispatch();

  const accessToken = loadItem('accessToken');

  useEffect(() => {
    if (accessToken) {
      dispatch(setAccessToken(accessToken));
    }
  }, [accessToken]);

  return (
    <div>
      <HeaderContainer/>
      <Routes>
        <Route path="/"/>
        <Route path="signup" element={<SignUpContainer/>}/>
        <Route path="login" element={<LogInContainer/>}/>
        <Route path="reservations" element={<Reservations/>}/>
        <Route path="*" element={<NotFound/>}/>
      </Routes>
    </div>
  );
}
