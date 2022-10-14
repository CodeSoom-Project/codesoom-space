import axios from 'axios';

import { loadItem } from './stoage';

const BASE_URL = 'https://api.codesoom-myseat.site';

const api = axios.create({
  baseURL: BASE_URL,
});

export const fetchRetrospection = ({ id, retrospections }: { id: number, retrospections: string }) => {
  const accessToken = loadItem('accessToken');

  return api({
    method: 'post',
    url: `/reservations/${id}/retrospectives`,
    headers: { Authorization: `Bearer ${accessToken}` },
    data: { retrospective: retrospections },
  });
};
