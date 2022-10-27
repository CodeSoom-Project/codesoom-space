import { render } from '@testing-library/react';

import Reservations from '.';

import { column } from '../../data/column';

describe('Reservations', () => {
  const renderReservations = () =>
    render(
      <Reservations/>,
    );

  it('List가 렌더링됩니다.', () => {
    const { container } = renderReservations();

    column.forEach(({ label }) => expect(container).toHaveTextContent(label));
  });
});
