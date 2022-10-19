import { Link } from 'react-router-dom';

import { Button } from '@mui/material';

import styled from '@emotion/styled';

const MainText = styled.h1({
  color: 'black',
  textShadow: '-1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000',

  'a': {
    color: 'inherit',
    textDecoration: 'none',
  },
  'a: hover': {
    color: 'grey',
  },
});

const TextDiv = styled.div({
  color: 'black',

  textShadow: '1px 1px 1px lightgrey',

  'a': {
    color: 'inherit',
    textDecoration: 'none',
  },
  'a: hover': {
    color: 'grey',
  },
});

const TextButton = styled(Button)({
  '&: hover': {
    color: 'grey',
  },
});

export default function Header({ accessToken, onClick }: any) {
  return (
    <header style={{ textAlign: 'center' }}>
      <MainText>
        <Link to="/">CodeSoom Space</Link>
      </MainText>
      {accessToken ? (
        <>
          <TextButton onClick={onClick}>
            로그아웃
          </TextButton>
          <Link to="/reservations" style={{ textDecoration: 'none' }}>
            <TextButton>예약</TextButton>
          </Link>
        </>
      ) : (
        <TextDiv>
          <Button>
            <Link to="/signup">회원 가입</Link>
          </Button>
          <Button>
            <Link to="/login">로그인</Link>
          </Button>
        </TextDiv>
      )}
    </header>
  );
}

