import * as React from 'react';

import styled from '@emotion/styled';

import { Button, Dialog } from '@mui/material';

const Content = styled.div({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'flex-end',
  backgroundColor: '#fffff',
  padding: '2rem',
});

interface Props {
  open: boolean;
  text: string;
  buttonText?: string;
  onClick: React.ReactEventHandler;
}

export default function Alert({ open, text, buttonText = '확인', onClick }: Props) {
  return (
    <Dialog open={open}>
      <Content>
        <p>
          {text}
        </p>
        <Button onClick={onClick} variant="contained">
          {buttonText}
        </Button>
      </Content>
    </Dialog>
  );
}
