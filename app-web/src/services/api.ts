import axios from 'axios';
import { loadItem, saveItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

const api = axios.create({
  baseURL: BASE_URL,
});

export const request = ({ ...options }) => {
  const token = loadItem('accessToken');
  api.defaults.headers.common.Authorization = token ? `Bearer ${token}` : '';
  const onSuccess = (response:any) => response;
  const onError = (error:any) => {
    return error;
  };

  return api(options).then(onSuccess).catch(onError);
};

export const apis = {
  getReservation: () => api.get('/seat-reservations'),
};

export const getSeatList = () => {
  return api.get('/seats');
};

export const getSeatDetail = ({ seatNumber }:{ seatNumber:string }) => {
  // return api.get(`/seats/${seatNumber}`);
  return request({ url: `/seats/${seatNumber}`, method: 'get' });
};

export const bookingSeatFn = async ({ seatNumber }: { seatNumber: any }) => {
  return request({ url: `/seat-reservation/${seatNumber}`, method: 'post' });
};

export const deleteReservationFn = async ({ seatNumber }: { seatNumber: any }) => {
  return request({ url: `/seat-reservation/${seatNumber}`, method: 'delete' });
};

export const loginUserFn = async ({ email, password }: { email: any, password: any }) => {
  return api
    .post('/login', {
      email,
      password,
    }).then((res)=> {
      const accessToken = res.data.token;
      saveItem('accessToken', accessToken);
    });
};

export const signUpUserFn = async ({
  email,
  password,
  name,
}: { email: any, password: any, name: string }) => {
  return api
    .post('/signup', {
      email,
      password,
      name,
    });
};
