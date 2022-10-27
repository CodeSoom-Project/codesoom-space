import { fireEvent, render } from '@testing-library/react';

import Modal from './Modal';

describe('modal', () => {
  const onClick = jest.fn();
  const onClose = jest.fn();

  beforeEach(() => {
    onClick.mockClear();
    onClose.mockClear();
  });

  const renderModal = () => render(
    <Modal
      open={true}
      title="제목"
      content='내용입니다'
      onClose={onClose}
      onClick={onClick}
    />,
  );

  it('title이 보인다', () => {
    const { getByTestId } = renderModal();

    expect(getByTestId('title')).toHaveTextContent('제목');
  });

  it('content가 보인다', () => {
    const { getByTestId } = renderModal();

    expect(getByTestId('content')).toHaveTextContent('내용입니다');
  });

  it('text가 보인다', () => {
    const { getByTestId } = renderModal();

    expect(getByTestId('text')).toHaveTextContent('확인');
  });

  it('onClick이벤트가 발동된다', () => {
    const { getByTestId } = renderModal();

    fireEvent.click(getByTestId('text'));

    expect(onClick).toBeCalled();
  });
});
