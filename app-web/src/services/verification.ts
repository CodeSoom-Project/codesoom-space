import { httpClient } from './api';

import { loadItem } from './stoage';

export const verification = async (token: string | null) => {
  const accessToken = loadItem('accessToken');

  await httpClient.post('/verification/email/verify', {
    token,
  },
  { headers: { Authorization: `Bearer ${accessToken}` },
  });
};
