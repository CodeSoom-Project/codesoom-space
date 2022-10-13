import { request } from './api';

export const fetchRetrospection = ({ id, retrospective }: { id: number, retrospective: string }) => {
  return request({
    method: 'post',
    url: `/res/reservations/${id}/retrospectiveservations`,
    data: { retrospective },
  });
};
