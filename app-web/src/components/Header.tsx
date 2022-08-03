import {Link} from "react-router-dom";
import {useDispatch} from "react-redux";
import {logout, setAccessToken} from "../authSlice";
import LogoutForm from "../LogOutForm";
import LoginForm from "../LoginForm";

function Header() {
  const dispatch = useDispatch();

  const accessToken = localStorage.getItem('accessToken');
  if (accessToken) {
    dispatch(setAccessToken(accessToken));
  }

  function handleClickLogout() {
    dispatch(logout)
  }


  return (
    <header>
      <h1>
        <Link to="/my-seat">코드숨 공부방 좌석 예약 앱</Link>
      </h1>
      {accessToken ? (
        <LogoutForm onClick={handleClickLogout}/>
      ) : (
        <LoginForm/>
      )}
    </header>
  );
}

export default Header;
