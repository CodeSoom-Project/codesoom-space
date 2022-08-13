import { Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logout } from '../authSlice';
import LogoutForm from '../LogOutForm';
import LoginForm from '../LoginForm';
import { useAppSelector } from '../hooks';

export default function Header() {
  const accessToken = useAppSelector((state) => state.auth.accessToken);

  const dispatch = useDispatch();

  function handleLogout() {
    dispatch(logout);
  }

  function handleRemoveAccessToken() {
    localStorage.removeItem('accessToken');
  }

  function handleClickLogout() {
    handleLogout();
    handleRemoveAccessToken();
  }

  return (
    <header>
      <h1>
        <Link to="/my-seat">코드숨 공부방 좌석 예약 앱</Link>
      </h1>
      {accessToken ? (
        <LogoutForm onClick={handleClickLogout}/>
      ) : (
        <LoginForm/>
      )}
    </header>
  );
}
