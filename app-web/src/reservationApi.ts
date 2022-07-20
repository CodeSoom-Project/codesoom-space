import axios from 'axios';

interface SeatType {
  seatNumber: number;
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

export const deleteReservationFn = async (seatNumber: any, userName: string) => {
  const response = await api.delete(`/seat-reservations/${seatNumber}`, {data: {userName}})
  return response
}
