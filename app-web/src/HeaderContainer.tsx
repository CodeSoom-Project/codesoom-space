import { useAppSelector } from './hooks';
import { useDispatch } from 'react-redux';

import { get } from './utils';

import { logout } from './authSlice';

import Header from './Header';

export default function HeaderContainer() {
  const { accessToken } = useAppSelector(get('auth'));

  const dispatch = useDispatch();

  function handleLogout() {
    dispatch(logout());
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
