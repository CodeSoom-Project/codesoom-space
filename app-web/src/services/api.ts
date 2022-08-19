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

export const getSeatDetail = (seatNumber:number) => {
  return request({ url: `/seat/${seatNumber}`, method: 'get' });
};

export const bookingSeatFn = async ({ seatNumber, checkIn, checkOut }:{ seatNumber:number, checkIn: string, checkOut:string }) => {
  try {
    return await request({ url: `/seat-reservation/${seatNumber}`, method: 'post', data: { checkIn, checkOut } });
  } catch (err) {
    throw window.alert(err.message);
  }
};

export const deleteReservationFn = async ({ seatNumber }: { seatNumber: number }) => {
  try {
    return await request({ url: `/seat-reservation/${seatNumber}`, method: 'delete' });
  }  catch (err) {
    throw window.alert(err.message);
  }
};

export const loginUserFn = async ({ email, password }: { email: string, password: string }) => {
  return api
    .post('/login', {
      email,
      password,
    }).then((res)=> {
      const accessToken = res.data.token;
      saveItem('accessToken', accessToken);
    }).catch((error)=> {
      window.alert(error.message);
    });
};

export const signUpUserFn = async ({
  email,
  password,
  name,
}: { email: string, password: string, name: string }) => {
  return api
    .post('/signup', {
      email,
      password,
      name,
    }).catch((error)=> {
      window.alert(error.message);
    });
};
