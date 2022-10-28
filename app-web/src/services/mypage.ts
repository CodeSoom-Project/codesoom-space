import { httpClient } from './api';

import { loadItem } from './stoage';

export const getUserInfo = async () => {
  const accessToken = loadItem('accessToken');

  const { data } = await httpClient.get('/mypage', {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return data;
};

export const verifyEmail = async () => {
  const accessToken = loadItem('accessToken');

  const response = await httpClient.post('/verification/email', {}, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });

  return response;
};
