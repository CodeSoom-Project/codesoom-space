import { render } from '@testing-library/react';

import { MemoryRouter } from 'react-router-dom';

import App from './App';

describe('App', () => {
  const renderApp = ({ path }: { path: string }) => render(
    <MemoryRouter initialEntries={[path]}>
      <App/>
    </MemoryRouter>,
  );

  context('경로가 / 라면', () => {
    it('login page가 렌더링된다', () => {
      const { container } = renderApp({ path: '/' });

      expect(container).toHaveTextContent('로그인');
    });
  });
});
