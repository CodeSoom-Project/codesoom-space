import { useQuery } from 'react-query';

import styled from '@emotion/styled';

import { Box, Button, TextField, CircularProgress } from '@mui/material';

import { getUserInfo } from '../../services/api';

const Container = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
  width: '400px',
  margin: '50px auto 0',
  '& .MuiBox-root': {
    marginTop: '5px',
  },

  '@media (max-width: 767px)': {
    width: '100%',
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default function MyPage() {

  const handleClick = () => {
    alert('아직 준비중입니다^^');
  };

  const { isLoading, data } = useQuery('mypage', getUserInfo, {
    retry: 1,
  });

  if (isLoading) {
    return <CircularProgress />;
  }

  const { name, email } = data;

  return (
    <Container>
      <Box>
        <TextField
          value={name}
          label="이름"
          size="small"
          disabled
        />
        <Box
          sx={{
            display: 'flex',
            alignItems: 'center',
          }}>
          <TextField
            value={email}
            label="e-mail"
            size="small"
            disabled
            margin="normal"
          />
          <Button onClick={handleClick}>인증하기</Button>
        </Box>
      </Box>
      <Button
        onClick={handleClick}
        sx={{
          margin: '30px auto 0',
        }}
        variant="contained">
         저장하기
      </Button>
    </Container>
  );
}
