import { render } from '@testing-library/react';

import ReservationsList from '.';

import { reservations } from '../../fixtures/reservations';

import columns from '../../data/columns';

describe('LoginFormContainer', () => {
  const renderReservationsList = () =>
    render(
      <ReservationsList
        pagination={{ page: 1, size: 10, totalPages: 1 }}
        reservations={reservations}
        columns={columns}
        onChange={() => {}}
        onClick={() => {}}
        open={false}
      />,
    );

  it('column이 보여집니다.', () => {
    const { container } = renderReservationsList();

    columns.forEach(({ label }) => expect(container).toHaveTextContent(label));
  });

  it('예약 목록이 보여집니다.', () => {
    const { container } = renderReservationsList();

    reservations.forEach(({ date, user: { name } }) => {
      expect(container).toHaveTextContent(name);
      expect(container).toHaveTextContent(date);
    });
  });
});
