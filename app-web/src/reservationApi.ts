import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

const getReservation = async () => {
  const response = await api.get('/seat-reservations');
  return response.data;
};

const deleteReservation = async ({ seatNumber, userName }: any) => {
  await api.delete(`/seat-reservations/${seatNumber}`, { data: { userName } });
};

export { getReservation, deleteReservation };
