import { render } from '@testing-library/react';

import LoginFormContainer from './LoginFormContainer';

describe('LoginFormContainer', () => {
  const renderLoginContainer = () =>
    render(
      <LoginFormContainer/>,
    );

  it('LoginFormContainer가 렌더링됩니다.', () => {
    const { container } = renderLoginContainer();

    expect(container).toHaveTextContent('아이디');
  });
});
