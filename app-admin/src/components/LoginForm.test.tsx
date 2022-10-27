import { render } from '@testing-library/react';

import LoginForm from './LoginForm';

describe('LoginForm', () => {
  const renderLoginForm = () =>
    render(
      <LoginForm/>,
    );

  it('LoginForm이 렌더링됩니다.', () => {
    const { container } = renderLoginForm();

    expect(container).toHaveTextContent('비밀번호');
  });
});
