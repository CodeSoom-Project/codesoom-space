import axios from 'axios';

const api = axios.create({
  baseUrl: 'http://localhost:8080/seat/',
});

export const getReservation = () => {
  api.get('/reservation').then((res) => res.data);
};
