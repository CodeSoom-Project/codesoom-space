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

const TextButton = styled(Button)({
  width: '80px',
  '&: hover': {
    color: 'grey',
  },
});

const LinkButton = styled(Link)({
  textDecoration: 'none',
});

export default function Header({ accessToken, onClick }: any) {
  return (
    <header style={{ textAlign: 'center' }}>
      <MainText>
        <Link to="/">CodeSoom Space</Link>
      </MainText>
      {accessToken ? (
        <>
          <LinkButton to="/reservations" >
            <TextButton>예약목록</TextButton>
          </LinkButton>
          <LinkButton to="/mypage" >
            <TextButton>마이페이지</TextButton>
          </LinkButton>
          <TextButton onClick={onClick}>
            로그아웃
          </TextButton>
        </>
      ) : (
        <>
          <LinkButton to="/signup">
            <TextButton>회원 가입</TextButton>
          </LinkButton>
          <LinkButton to="/login">
            <TextButton>로그인</TextButton>
          </LinkButton>
        </>
      )}
    </header>
  );
}
