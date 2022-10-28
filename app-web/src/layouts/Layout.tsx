import React from 'react';

import styled from '@emotion/styled';

interface Props {
  children: React.ReactNode
}

const Wrapper = styled.div({
  display: 'flex',
  flexDirection: 'column',
  minHeight: '80vh',
});

export default function Layout({ children }: Props) {
  return (
    <Wrapper>
      {children}
    </Wrapper>
  );
}
