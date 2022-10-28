import axios from 'axios';

const BASE_URL = 'https://api.codesoom-myseat.site/';

export const httpClient = axios.create({
  baseURL: BASE_URL,
});
