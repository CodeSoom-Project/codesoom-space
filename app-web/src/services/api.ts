import axios from 'axios';

import { store } from '../store';

import { loadItem } from './stoage';

import { setTokenExpired } from '../redux/authSlice';

const BASE_URL = 'https://api.codesoom-myseat.site';

export const httpClient = axios.create({
  baseURL: BASE_URL,
});

httpClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response.status === 401) {
      store.dispatch(setTokenExpired());
      return;
    }

    return Promise.reject(error);
  });

export const request = ({ ...options }) => {
  const token = loadItem('accessToken');
  httpClient.defaults.headers.common.Authorization = token ? `Bearer ${token}` : '';
  const onSuccess = (response: any) => response;
  const onError = (error: any) => {
    return error;
  };

  return httpClient(options).then(onSuccess).catch(onError);
};

export const getSeats = () => {
  return httpClient.get('/seats');
};

export const getSeatDetail = (seatNumber: number) => {
  return request({ url: `/seat/${seatNumber}`, method: 'get' });
};

export const seatReservation = async ({ seatNumber }: { seatNumber: number }) => {
  try {
    return await request({ url: `/seat-reservation/${seatNumber}`, method: 'post' });
  } catch (err) {
    alert(err.message);
  }
};

export const cancelReservation = async ({ seatNumber }: { seatNumber: number }) => {
  try {
    return await request({ url: `/seat-reservation/${seatNumber}`, method: 'delete' });
  } catch (err) {
    alert(err.message);
  }
};

export const login = async ({ email, password }: { email: string, password: string }) => {
  const { data } = await httpClient.post('/login', { email, password });
  return data.accessToken;
};

export const signUp = async (formData: { email: string, password: string, name: string }) => {
  const { data } = await httpClient.post('/signup', formData);
  return data;
};
