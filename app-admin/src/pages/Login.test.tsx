import { render } from '@testing-library/react';

import Login from './Login';

describe('Login', () => {
  const renderLogin = () =>
    render(
      <Login/>,
    );

  it('로그인 페이지가 렌더링됩니다.', () => {
    const { container } = renderLogin();

    expect(container).toHaveTextContent('로그인');
  });
});
