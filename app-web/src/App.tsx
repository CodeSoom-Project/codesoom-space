import './App.css';

import { useEffect } from 'react';

import { Routes, Route } from 'react-router-dom';

import PrivateRoute from './routes/PrivateRoute';

import { setAccessToken } from './redux/authSlice';

import { useAppDispatch, useAppSelector } from './hooks';

import { get } from './utils';

import HeaderContainer from './HeaderContainer';

import NotFound from './NotFound';
import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import Reservations from './pages/Reservations';

export default function App() {
  const dispatch = useAppDispatch();

  const { accessToken } = useAppSelector(get('auth'));

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
