import { useDispatch } from 'react-redux';

import { useNavigate } from 'react-router-dom';

import { logout } from './redux/authSlice';

import { get } from './utils';

import { useAppSelector } from './hooks';

import Header from './Header';

export default function HeaderContainer() {
  const navigate = useNavigate();

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

    navigate('/login');
  }

  return (
    <Header
      accessToken={accessToken}
      onClick={handleClickLogout}
    />
  );
}
