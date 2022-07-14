import axios from 'axios';

interface SeatType {
  seatNumber: number;
  userName: string;
}

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

const getReservation = async () => {
  const { data } = await api.get('/seat-reservations');
  return data;
};

const deleteReservation = (seatNumber: number, data: any) => {
  api.delete(`/${seatNumber}`, data);
};

export { getReservation, deleteReservation };
