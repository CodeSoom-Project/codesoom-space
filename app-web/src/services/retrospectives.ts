import { httpClient } from './api';

import { loadItem } from './stoage';

export const retrospectivesKeys = {
  retrospectivesById: (id: number) => ['retrospectives', id] as const,
};

export const fetchRetrospectives = async ({ id, content }: { id: number, content: string }) => {
  const accessToken = loadItem('accessToken');

  const response = await httpClient.post(`reservations/${id}/retrospectives`, {
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};

export const getRetrospective = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await httpClient.get(`/reservations/${id}/retrospectives`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};

export const updateRetrospectives = ({ id, content }: { id: number, content: string }) => {
  const accessToken = loadItem('accessToken');

  return httpClient.put(`/reservations/${id}/retrospectives`, { content }, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });
};
