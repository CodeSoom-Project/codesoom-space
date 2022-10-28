import { useQuery } from 'react-query';

import styled from '@emotion/styled';

import { Box, CircularProgress, TextField } from '@mui/material';

import { getUserInfo } from '../../services/mypage';

import Layout from '../../layouts/Layout';

import EmailField from '../../components/mypage/EmailFiled';

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
  const { isLoading, data } = useQuery('mypage', getUserInfo, {
    retry: 1,
  });

  if (isLoading) {
    return <CircularProgress />;
  }

  const { name, email, emailVerified } = data;

  return (
    <Layout>
      <Container>
        <Box>
          <TextField
            value={name}
            label="이름"
            size="small"
            disabled
          />
          <EmailField
            emailVerified={emailVerified}
            email={email} />
        </Box>
      </Container>
    </Layout>
  );
}
