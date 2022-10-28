import { httpClient } from './api';

import { loadItem } from '../services/storage';

export const getRetrospectives = async (id: number) => {
  const accessToken = loadItem('accessToken');

  const { data } = await httpClient.get(`admin/reservations/${id}/retrospectives`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return data;
};
