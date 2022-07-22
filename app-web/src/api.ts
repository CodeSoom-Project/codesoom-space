import axios from 'axios';

interface SeatType {
  seatNumber: string | number;
  userName: string;
}

interface deleteReservationVariables {
  seatNumber: number | string,
  userName: string,
}

const api = axios.create({
  baseURL: 'https://api.codesoom-myseat.site',
});

export const apis = {
  getReservation: () => api.get('/seat-reservations'),
};

export const deleteReservationFn = (seatNumber: string | number, userName: string) => {
  return api.delete(`/seat-reservation/${seatNumber}`, {data: {userName}})
}

export const loginUser = async ({email, password}: { email: any, password: any }) => {
  try {
    const {data} = await api.post("/user/signin", {email, password})
    return data;
  } catch (error: any) {
    throw Error(error.response.data.message)
  }
}

export const signupUser = async ({
                                   email,
                                   password,
                                   passwordCheck,
                                   name
                                 }: { email: any, password: any, passwordCheck: any, name: string }) => {
  try {
    const {data} = await api.post("/user/signup", {email, password, name, passwordCheck})
    return data;
  } catch (error: any) {
    throw Error(error.response.data.message)
  }
}
