import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

const api = axios.create({
  baseURL: BASE_URL,
});

export const reservationsKeys = {
  reservationsById: (id: number) => ['retrospectives', id] as const,
};

export const getReservation = async () => {
  const accessToken = loadItem('accessToken');

  const { data } = await api.get('/reservations', {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return data;
};

export const fetchReservation = async ({ date, content }: { date: string, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await api.post('/reservations', {
    date,
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const updateReservation = async ({ id, date, content }: { id: number, date: string, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await api.put(`/reservations/${id}`, {
    date,
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const getReservations = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await api.get(`reservations/${id}`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};
