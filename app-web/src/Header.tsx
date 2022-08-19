import { Link } from 'react-router-dom';
import LogoutForm from './LogOutForm';
import LoginForm from './LoginForm';

export default function Header({ accessToken, onClick }:any) {
  return (
    <header>
      <h1>
        <Link to="/my-seat">코드숨 공부방 좌석 예약 앱</Link>
      </h1>
      {accessToken ? (
        <LogoutForm onClick={()=>onClick}/>
      ) : (
        <LoginForm/>
      )}
    </header>
  );
}
