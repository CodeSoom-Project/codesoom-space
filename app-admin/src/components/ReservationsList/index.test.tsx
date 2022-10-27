import { render } from '@testing-library/react';

import ReservationsList from '.';

import { reservations } from '../../fixtures/reservations';

import { column } from '../../data/column';

describe('LoginFormContainer', () => {
  const renderReservationsList = () =>
    render(
      <ReservationsList
        reservations={reservations}
        column={column}
      />,
    );

  it('column이 보여집니다.', () => {
    const { container } = renderReservationsList();

    column.forEach(({ label }) => expect(container).toHaveTextContent(label));
  });

  it('예약 목록이 보여집니다.', () => {
    const { container } = renderReservationsList();

    reservations.forEach(({ date, user: { name } }) => {
      expect(container).toHaveTextContent(name);
      expect(container).toHaveTextContent(date);
    });
  });
});
