export default function SignUp({ register, errors, handleSubmit, error, signUp, watch, setError }:
{ register: any, errors: any, handleSubmit: any, error: any, signUp: any, watch: any, setError: any }) {
  return (
    <>
      <main style={{ padding: '1rem 0' }}>
        <form
          onSubmit={handleSubmit(async (data: any) => {
            await signUp({
              email: data.email,
              password: data.password,
              name: data.name,
            });
            console.log('회원가입동작');
          })
          }>

          <table>
            <tbody>
              <tr>
                <td><label htmlFor="name">이름</label></td>
                <td>
                  <input {...register('name', {
                    required: true,
                  })} type="text" id="name"/>
                </td>
              </tr>

              <tr>
                <td><label htmlFor="email">Email</label></td>
                <td>
                  <input {...register('email', {
                    required: true,
                  },
                  )} type="email" id="email"/>
                </td>
              </tr>

              <tr>
                <td><label htmlFor="password">비밀번호</label></td>
                <td>
                  <input {...register('password', {
                    required: true,
                  })} type="password" id="password"/>
                </td>
              </tr>

              <tr>
                <td><label htmlFor="passwordMatch">비밀번호 확인</label></td>
                <td>
                  <input {...register('passwordMatch', {
                    required: true,
                  })} type="password" id="passwordMatch"/>
                </td>
              </tr>
              <tr>
                <td></td>
                <td>
                  <p>{errors.passwordMatch?.message}</p>
                  {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
                  {errors.email?.type === 'required' && '이메일을 입력 해 주세요'}
                  {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}</td>
              </tr>

              <tr>
                <td></td>
                <td>
                  <button type="submit">회원 가입하기</button>
                </td>
              </tr>
            </tbody>
          </table>
        </form>
      </main>
    </>
  );
}
