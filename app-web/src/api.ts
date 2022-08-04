import axios from 'axios';

const BASE_URL = 'https://api.codesoom-myseat.site'

const api = axios.create({
  baseURL: BASE_URL,
});

export const apis = {
  getReservation: () => api.get('/seat-reservations'),
};

export const getSeatList = async () => {
  return await api.get('/seats')
}

export const deleteReservationFn = async ({seatNumber, userName}: { seatNumber: any, userName: string }) => {
  return await api.delete(`/seat-reservation/${seatNumber}`, {data: {userName}})
}

export const loginUserFn = async ({email, password}: { email: any, password: any }) => {
  return await api
    .post("/login", {
      email,
      password,
    })
}

export const signUpUserFn = async ({
                                     email,
                                     password,
                                     name
                                   }: { email: any, password: any, name: string }) => {
  return await api
    .post("/signup", {
      email,
      password,
      name,
    })
}
