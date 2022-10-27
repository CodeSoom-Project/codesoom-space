import { render } from '@testing-library/react';

import ReservationsContainer from '.';

import { reservations } from '../../fixtures/reservations';

describe('LoginFormContainer', () => {
  const renderReservationsContainer = () =>
    render(
      <ReservationsContainer/>,
    );

  it('예약 목록이 보여집니다.', () => {
    const { container } = renderReservationsContainer();

    reservations.forEach(({ date, user: { name } }) => {
      expect(container).toHaveTextContent(name);
      expect(container).toHaveTextContent(date);
    });
  });
});
