import Header from './components/Header';
import {useForm} from "react-hook-form";
import {useMutation} from "react-query";
import {signupUser} from "./reservationApi";

export default function SignUp() {
  const {register, formState: {errors}, handleSubmit} = useForm();

  const {isLoading, error, isError, mutateAsync, data} = useMutation('signup', signupUser);
  console.log("data", data);
  console.log(error);


  return (
    <>
      <Header/>
      <main style={{padding: "1rem 0"}}>
        <h2>회원 가입</h2>
        <form
          onSubmit={handleSubmit(async (data) => {
            await mutateAsync({
              email: data.email,
              password: data.password,
              name: data.name,
            })
          })
          }>

          <label htmlFor="name">이름</label>
          <input {...register("name", {
            required: true,
          })} type="text" id="name"/>

          <label htmlFor="email">Email</label>
          <input {...register("email", {
              required: true,
            }
          )} type="email" id="email"/>

          <label htmlFor="password">비밀번호</label>
          <input {...register("password", {
            required: true,
          })} type="password" id="password"/>

          <label htmlFor="password">비밀번호 확인</label>
          <input {...register("passwordCheck", {
            required: true,
          })} type="password" id="passwordCheck"/>

          {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
          {errors.email?.type === 'required' && '이메일을 입력 해 주세요'}
          {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}

          <button type="submit">회원 가입하기</button>
        </form>
      </main>
    </>
  )
}
