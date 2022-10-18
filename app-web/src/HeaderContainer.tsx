import { useEffect } from 'react';

import { useAppSelector } from './hooks';
import { useDispatch } from 'react-redux';

import { get } from './utils';

import { logout, setAccessToken } from './authSlice';

import Header from './Header';

import { loadItem } from './services/stoage';

export default function HeaderContainer() {
  const { accessToken } = useAppSelector(get('auth'));

  const dispatch = useDispatch();

  const token = loadItem('accessToken');

  useEffect(() => {
    if (token) {
      dispatch(setAccessToken(token));
    }
  }, [token]);

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
