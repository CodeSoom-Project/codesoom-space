import Header from './components/Header';
import {useForm} from "react-hook-form";

export default function SignIn() {
  const {register, formState: {errors}, handleSubmit} = useForm();


  return (
    <>
      <Header/>
      <main style={{padding: "1rem 0"}}>
        <h2>로그인</h2>
        <form
          onSubmit={handleSubmit((data) => alert(JSON.stringify(data)))
          }>

          <label htmlFor="email">Email</label>
          <input {...register("email", {
              required: true,
            }
          )} type="email" id="email"/>

          <label htmlFor="password">비밀번호</label>
          <input {...register("password", {
            required: true,
          })} type="password" id="password"/>
          
          {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
          {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}

          <button type="submit">로그인 하기</button>
        </form>
      </main>
    </>
  )
}
