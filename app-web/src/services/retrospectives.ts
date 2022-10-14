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
