import { useMutation } from 'react-query';

import { AxiosError } from 'axios';

import styled from '@emotion/styled';

import { useAppDispatch, useAppSelector } from '../../hooks';

import { Box, Button, TextField } from '@mui/material';

import { verifyEmail } from '../../services/mypage';
import { sendEmail } from '../../redux/mypageSlice';
import { get } from '../../utils';
import { useState } from 'react';

interface Props {
  email: string
}

const ExpirationTime = styled.p({
  fontSize: '.7rem',
  color: '#F15F5F',
});

export default function EmailField({ email }: Props) {
  const [time, setTime] = useState();
  const dispatch = useAppDispatch();

  const { emailVerification } = useAppSelector(get('mypage'));

  const { mutate: verifyEmailMutate } = useMutation(verifyEmail, {
    onSuccess: ({ data }) => {
      setTime(data.expirationMinutes);
      dispatch(sendEmail('success'));
      alert(`인증 메일이 전송되었습니다. 이메일을 확인해주세요. ${time}분입니다.`);
    },
    onError: (error: AxiosError) => {
      alert(error.response?.data);
    },
  });

  const handleClick = () => {
    verifyEmailMutate();
  };

  return (
    <Box
      sx={{
        display: 'flex',
        alignItems: 'baseline',
      }}>
      <TextField
        value={email}
        label="e-mail"
        size="small"
        disabled
        margin="normal"
      />
      <Button onClick={handleClick}>인증하기</Button>

      {emailVerification === 'success' && (
        <ExpirationTime>유효시간 {time}분</ExpirationTime>
      )}
    </Box>
  );
}
