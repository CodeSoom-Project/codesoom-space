import { Link } from 'react-router-dom';
import './index.css';

export default function Header({ accessToken, onClick }:any) {
  return (
    <header>
      <h1>
        <Link to="/my-seat">코드숨 공부방</Link>
      </h1>
      <Link to="/signup">회원 가입</Link> | {' '}

      {accessToken ? (
        <button onClick={onClick}>로그아웃</button>
      ) : (
        <Link to="/signin">로그인</Link>
      )}
    </header>
  );
}

