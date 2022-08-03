import {Link} from "react-router-dom";

const LoginForm = () => {
  return (
    <>
      <Link to="/signup">회원 가입</Link> | {" "}
      <Link to="/signin">로그인</Link>
    </>
  );
};

export default LoginForm;
