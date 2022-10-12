import { Link } from 'react-router-dom';

import Button from './components/Button';

export default function Header({ accessToken, onClick }:any) {
  return (
    <header>
      <h1>
        <Link to="/my-seat">코드숨 공부방</Link>
      </h1>
      <Link to="/signup">회원 가입</Link> | {' '}

      {accessToken ? (
        <Button onClick={onClick}>로그아웃</Button>
      ) : (
        <Link to="/login">로그인</Link>
      )}
    </header>
  );
}

