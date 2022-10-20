import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

export const retrospectivesKeys = {
  retrospectivesById: (id: number) => ['retrospectives', id] as const,
};

export const fetchRetrospectives = async ({ id, content }: { id: number, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await axios.post(`${BASE_URL}/reservations/${id}/retrospectives`, {
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const getRetrospective = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await axios.get(`${BASE_URL}/reservations/${id}/retrospectives`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};
