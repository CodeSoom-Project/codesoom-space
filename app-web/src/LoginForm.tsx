import { Link } from 'react-router-dom';

export default function LoginForm()  {
  return (
    <>
      <Link to="/signup">회원 가입</Link> | {' '}
      <Link to="/signin">로그인</Link>
    </>
  );
}


