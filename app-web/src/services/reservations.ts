import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

const api = axios.create({
  baseURL: BASE_URL,
});

export const getReservation = async () => {
  const accessToken = loadItem('accessToken');
  const { data } = await api({
    method: 'get',
    url: '/reservations',
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};

export const fetchReservation = async ({ date, plan }:{ date : string, plan : string }) => {
  const accessToken = loadItem('accessToken');

  const response = await api({
    method: 'post',
    url: '/reservations',
    headers: { Authorization: `Bearer ${accessToken}` },
    data: { date, plan },
  });

  return response;
};
