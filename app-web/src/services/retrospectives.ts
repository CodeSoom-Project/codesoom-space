import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

const api = axios.create({
  baseURL: BASE_URL,
});

export const retrospectivesKeys = {
  retrospectivesById: (id: number) => ['retrospectives', id] as const,
};

export const fetchRetrospectives = async ({ id, content }: { id: number, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await api.post(`reservations/${id}/retrospectives`, {
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const getRetrospective = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await api.get(`/reservations/${id}/retrospectives`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};

export const updateRetrospectives = ({ id, content }: { id: number, content: string }) => {
  const accessToken = loadItem('accessToken');

  return api.put(`/reservations/${id}/retrospectives`, { content }, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
};
