import './App.css';

import { useEffect } from 'react';

import { Routes, Route, useNavigate } from 'react-router-dom';


import PrivateRoute from './routes/PrivateRoute';

import { logout, setAccessToken } from './redux/authSlice';

import { loadItem, removeItem } from './services/stoage';

import { useAppDispatch, useAppSelector } from './hooks';

import { get } from './utils';

import NotFound from './NotFound';
import Reservations from './pages/Reservations';

import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import HeaderContainer from './HeaderContainer';

import Alert from './components/Alert';

export default function App() {
  const navigate = useNavigate();

  const dispatch = useAppDispatch();

  const { tokenExpired } = useAppSelector(get('auth'));

  const removeToken = () => {
    removeItem('accessToken');
  };

  const handleClickLogout = () => {
    removeToken();

    dispatch(logout());

    navigate('/login');
  };

  const accessToken = loadItem('accessToken');
  useEffect(() => {
    dispatch(setAccessToken(loadItem('accessToken')));

  }, []);

  return (
    <div>
      <Alert
        open={tokenExpired}
        content="토큰이 만료되었습니다. 다시로그인해주세요."
        onClick={handleClickLogout}
      />

      <HeaderContainer/>
      <Routes>
        <Route path="/"/>
        <Route path="signup" element={<SignUpContainer/>}/>
        <Route path="login" element={<LogInContainer />} />
        <Route
          path="reservations"
          element={
            <PrivateRoute accessToken={accessToken}>
              <Reservations />
            </PrivateRoute>}
        />
        <Route path="*" element={<NotFound/>}/>
      </Routes>
    </div>
  );
}
