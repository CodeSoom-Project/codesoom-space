import {Link} from "react-router-dom";

function Header() {
  return (
    <header>
      <h1>
        <Link to="/">코드숨 공부방 좌석 예약 앱</Link>
      </h1>
      <nav
        style={{
          borderBottom: '1px solid',
          paddingBottom: '1rem',
        }}
      >
        <Link to="/signUp">회원 가입</Link> | {" "}
        <Link to="/signIn">로그인</Link>
      </nav>
    </header>
  );
}

export default Header;
