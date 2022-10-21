import './App.css';

import { useEffect } from 'react';

import { Routes, Route, useNavigate } from 'react-router-dom';


import { useAppDispatch, useAppSelector } from './hooks';

import { get } from './utils';

import { logout, setAccessToken } from './redux/authSlice';


import { loadItem } from './services/stoage';

import PrivateRoute from './routes/PrivateRoute';

import NotFound from './NotFound';
import Reservations from './pages/Reservations';

import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import Alert from './components/Alert';
import HeaderContainer from './HeaderContainer';

export default function App() {
  const navigate = useNavigate();

  const dispatch = useAppDispatch();

  const accessToken = loadItem('accessToken');

  const { isTokenExpired } = useAppSelector(get('auth'));

  const removeToken = () => {
    localStorage.removeItem('accessToken');
  };

  const handleClickLogout = () => {
    removeToken();

    dispatch(logout());

    navigate('/login');
  };

  useEffect(() => {
    dispatch(setAccessToken(loadItem('accessToken')));
  }, []);

  return (
    <div>
      <Alert
        open={isTokenExpired}
        text='토큰이 만료되었습니다. 다시로그인해주세요.'
        onClick={handleClickLogout}
      />

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
