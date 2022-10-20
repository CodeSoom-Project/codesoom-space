import { instance } from './api';

import { loadItem } from './stoage';

export const retrospectivesKeys = {
  retrospectivesById: (id: number) => ['retrospectives', id] as const,
};

export const fetchRetrospectives = async ({ id, content }: { id: number, content: string }) => {
  const accessToken = loadItem('accessToken');

  const { data } = await instance.post(`reservations/${id}/retrospectives`, {
    content,
  }, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};

export const getRetrospective = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await instance.get(`/reservations/${id}/retrospectives`, {
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
