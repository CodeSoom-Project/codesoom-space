export default function SignUp({register, errors, handleSubmit, error, mutate, watch, setError}:
                                 { register: any, errors: any, handleSubmit: any, error: any, mutate: any, watch: any, setError: any }) {

  return (
    <>
      <main style={{padding: "1rem 0"}}>
        <h2>회원 가입</h2>
        <form
          onSubmit={handleSubmit(async (data: any) => {
            await mutate({
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

          <label htmlFor="passwordCheck">비밀번호 확인</label>
          <input {...register("passwordCheck", {
            required: true,
          })} type="password" id="passwordCheck"/>
          <p>{errors.passwordCheck?.message}</p>


          {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
          {errors.email?.type === 'required' && '이메일을 입력 해 주세요'}
          {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}

          <button type="submit">회원 가입하기</button>
        </form>
      </main>
    </>
  )
}
