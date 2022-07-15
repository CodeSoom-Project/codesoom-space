import axios from 'axios';

interface SeatType {
  seatNumber: number;
  userName: string;
}

const api = axios.create({
  baseURL: 'http://15.164.164.136:8080/',
});

export const apis = {
  getReservation: () => api.get('/seat-reservations'),
  deleteReservation: (seatNumber: any, data: any) => api.delete(`/seat-reservations${seatNumber}`, data),
};
