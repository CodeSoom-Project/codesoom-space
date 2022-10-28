import { useMutation } from 'react-query';

import { AxiosError } from 'axios';

import styled from '@emotion/styled';

import { Box, Button, TextField } from '@mui/material';

import { verifyEmail } from '../../services/mypage';

interface Props {
  emailVerified: boolean,
  email: string
}

const Container = styled(Box)({
  display: 'flex',
  alignItems: 'baseline',
});

const Message = styled.p({
  marginLeft: 5,
  fontSize: '.7em',
  color: '#000000',
});

export default function EmailField({ emailVerified, email }: Props) {

  const { mutate: verifyEmailMutate } = useMutation(verifyEmail, {
    onSuccess: () => {
      alert('인증 메일이 전송되었습니다. 10분 내에 인증을 완료해 주세요.');
    },
    onError: (error: AxiosError) => {
      alert(error.response?.data);
    },
  });

  const handleClick = () => {
    verifyEmailMutate();
  };

  return (
    <Container>
      <TextField
        value={email}
        label="e-mail"
        size="small"
        disabled
        margin="normal"
      />
      {emailVerified && (<Message>인증완료</Message>)}
      {!emailVerified && (<Button onClick={handleClick}>인증하기</Button>)}
    </Container>
  );
}
