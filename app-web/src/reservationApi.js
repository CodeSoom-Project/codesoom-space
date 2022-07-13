import axios from 'axios';

const api = axios.create({
  baseUrl: 'http://localhost:8080',
});

const getReservation = () => {
  api.get('/seat-reservations').then((res) => res.data);
};

export { getReservation };
