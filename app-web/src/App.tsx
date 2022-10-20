import './App.css';

import { useEffect } from 'react';

import styled from '@emotion/styled';

import { Routes, Route, useNavigate } from 'react-router-dom';

import { Button, Dialog } from '@mui/material';

import { useAppDispatch, useAppSelector } from './hooks';

import { get } from './utils';

import { logout, setAccessToken, setIsTokenExpired } from './authSlice';

import { setAccessToken } from './redux/authSlice';

import { loadItem } from './services/stoage';

import PrivateRoute from './routes/PrivateRoute';

import NotFound from './NotFound';
import Reservations from './pages/Reservations';

import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import HeaderContainer from './HeaderContainer';


const TokenExpiredDialogContent = styled.div({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'flex-end',
  backgroundColor: '#fffff',
  padding: '2rem',
});

export default function App() {
  const navigate = useNavigate();

  const dispatch = useAppDispatch();

  const accessToken = loadItem('accessToken');

  const { isTokenExpired } = useAppSelector(get('auth'));

  const handleClickLogout = () => {
    localStorage.removeItem('accessToken');

    dispatch(logout());

    navigate('/login');

    window.location.reload();
  };

  useEffect(() => {
    dispatch(setAccessToken(loadItem('accessToken')));
  }, []);

  return (
    <div>
      <Dialog open={isTokenExpired}>
        <TokenExpiredDialogContent>
          <p>
              토큰이 만료되었습니다. 다시로그인해주세요
          </p>
          <Button onClick={handleClickLogout} variant="contained">
              확인
          </Button>
        </TokenExpiredDialogContent>
      </Dialog>

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
