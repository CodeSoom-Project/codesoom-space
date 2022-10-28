import { httpClient } from './api';

export const getReservations = async ({ page, size }: { page: number; size: number }) => {
  const accessToken = localStorage.getItem('accessToken');

  const { data } = await httpClient.get(`/admin/reservations?page=${page}&size=${size}`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};

export const x = () => { };
