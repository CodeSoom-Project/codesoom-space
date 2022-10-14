import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

const api = axios.create({
  baseURL: BASE_URL,
});

export const fetchRetrospectives = ({ id, retrospectives }: { id: number, retrospectives: string }) => {
  const accessToken = loadItem('accessToken');

  return api({
    method: 'post',
    url: `/reservations/${id}/retrospectives`,
    headers: { Authorization: `Bearer ${accessToken}` },
    data: { retrospectives: retrospectives },
  });
};

export const retrospectivesKeys = {
  retrospectivesById: (id : number) => ['retrospectives', id] as const,
};

export const getRetrospective = async (id : number) => {
  const accessToken = loadItem('accessToken');
  const { data } = await api({
    method: 'get',
    url: `reservations/${id}/retrospectives`,
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};
