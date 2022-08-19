import { useDispatch } from 'react-redux';
import { logout } from './authSlice';
import Header from './Header';
import { useAppSelector } from './hooks';

export default function HeaderContainer() {
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
    <Header
      accessToken={accessToken}
      onClick={handleClickLogout}
    />
  );
}
