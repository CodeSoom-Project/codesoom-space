import { ReactEventHandler } from 'react';

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
  content: string;
  text?: string;
  onClick: ReactEventHandler;
}

export default function Alert({ open, content, text = '확인', onClick }: Props) {
  return (
    <Dialog open={open}>
      <Content>
        <p>
          {content}
        </p>
        <Button onClick={onClick} variant="contained">
          {text}
        </Button>
      </Content>
    </Dialog>
  );
}
