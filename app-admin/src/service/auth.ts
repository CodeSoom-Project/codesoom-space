import { httpClient } from './api';

import { LoginFields } from '../redux/authSlice';

export const postLogin = async ({ email, password }: LoginFields) => {
  const { data } = await httpClient.post('/login', { email, password });

  return data;
};

export const x = () => { };
