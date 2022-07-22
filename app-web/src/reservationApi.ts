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
