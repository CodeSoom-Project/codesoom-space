import './App.css';

import { useEffect } from 'react';

import { useAppDispatch } from './hooks';

import { setAccessToken } from './redux/authSlice';

import HeaderContainer from './HeaderContainer';

import { Routes, Route } from 'react-router-dom';

import NotFound from './NotFound';
import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import Reservations from './pages/Reservations';

import PrivateRoute from './routes/PrivateRoute';
import { useSelector } from 'react-redux';
import { get } from './utils';

export default function App() {
  const dispatch = useAppDispatch();

  const { accessToken } = useSelector(get('auth'));

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
