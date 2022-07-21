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
  return api.delete(`/seat-reservations/${seatNumber}`, {data: {userName}})
}

const loginUser = async ({email, password}: { email: any, password: any }) => {
  try {
    const {data} = await api.post("/user/signin", {email, password})
  } catch (error: any) {
    throw Error(error.response.data.message)
  }
}
