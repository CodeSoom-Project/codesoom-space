import './App.css';

import { Route, Routes, useNavigate } from 'react-router-dom';

import PrivateRoute from './routes/PrivateRoute';

import { logout } from './redux/authSlice';

import { removeItem } from './services/stoage';

import { useAppDispatch, useAppSelector } from './hooks';

import { get } from './utils';

import NotFound from './NotFound';
import Reservations from './pages/Reservations';
import MyPage from './pages/MyPage';
import MainPage from './pages/MainPage';
import EmailVerificationPage from './pages/Verification/EmailVerificationPage';

import LogInContainer from './logInContainer';
import SignUpContainer from './signUpContainer';
import HeaderContainer from './HeaderContainer';

import Alert from './components/Alert';
import Footer from './components/Footer';

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

  return (
    <div>
      <Alert
        open={tokenExpired}
        content="토큰이 만료되었습니다. 다시로그인해주세요."
        onClick={handleClickLogout}
      />

      <HeaderContainer/>
      <Routes>
        <Route path="/" element={<MainPage/>}/>
        <Route path="signup" element={<SignUpContainer/>}/>
        <Route path="login" element={<LogInContainer />} />
        <Route path="verification/email" element={<EmailVerificationPage/>} />
        <Route
          path="reservations"
          element={
            <PrivateRoute>
              <Reservations />
            </PrivateRoute>}
        />
        <Route
          path="mypage"
          element={
            <PrivateRoute>
              <MyPage />
            </PrivateRoute>}
        />
        <Route path="*" element={<NotFound/>}/>
      </Routes>
      <Footer />
    </div>
  );
}
