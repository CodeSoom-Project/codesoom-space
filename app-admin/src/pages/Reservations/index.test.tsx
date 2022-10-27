import { render } from '@testing-library/react';

import Reservations from '.';

import columns from '../../data/columns';

describe('Reservations', () => {
  const renderReservations = () =>
    render(
      <Reservations/>,
    );

  it('List가 렌더링됩니다.', () => {
    const { container } = renderReservations();

    columns.forEach(({ label }) => expect(container).toHaveTextContent(label));
  });
});
