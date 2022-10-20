import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

export const reservationsKeys = {
  reservationsById: (id: number) => ['retrospectives', id] as const,
};

export const getReservation = async () => {
  const accessToken = loadItem('accessToken');

  const { data } = await axios.get(`${BASE_URL}/reservations`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return data;
};

export const fetchReservation = async ({ date, content }: { date: string, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await axios.post(`${BASE_URL}/reservations`, {
    date,
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const updateReservation = async ({ id, date, content }: { id: number, date: string, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await axios.put(`${BASE_URL}/reservations/${id}`, {
    date,
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const getReservations = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await axios.get(`${BASE_URL}/reservations/${id}`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};
