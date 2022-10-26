import { render } from '@testing-library/react';

import App from './App';

describe('App', () => {
  const renderApp = () => render(<App/>);

  it('좌석 예약 어드민이 보인다', () => {
    const { container } = renderApp();

    expect(container).toHaveTextContent('좌석 예약 어드민');
  });
});
