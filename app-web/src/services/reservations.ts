import { instance } from './api';

import { loadItem } from './stoage';

export const reservationsKeys = {
  reservationsById: (id: number) => ['retrospectives', id] as const,
};

export const getReservation = async () => {
  const accessToken = loadItem('accessToken');

  const { data } = await instance.get('/reservations', {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return data;
};

export const fetchReservation = async ({ date, content }: { date: string, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await instance.post('/reservations', {
    date,
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const updateReservation = async ({ id, date, content }: { id: number, date: string, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await instance.put(`/reservations/${id}`, {
    date,
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const getReservations = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await instance.get(`reservations/${id}`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};

export const cancelReservation = async (id: number) => {
  const accessToken = loadItem('accessToken');

  await api.patch(`reservations/${id}`, {}, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
};
