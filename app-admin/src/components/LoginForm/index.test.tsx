import { render } from '@testing-library/react';

import LoginForm from '.';

describe('LoginForm', () => {
  const renderLoginForm = () =>
    render((
      <LoginForm
        handleSubmit={() => {}}
        onChange={() => {}}
        fields={{
          email: '',
          password: '',
        }}
      />
    ));

  it('LoginForm이 렌더링됩니다.', () => {
    const { container } = renderLoginForm();

    expect(container).toHaveTextContent('비밀번호');
  });
});
