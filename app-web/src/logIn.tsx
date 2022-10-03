export default function LogIn({ register, errors, handleSubmit, login }:
{ register: any, errors: any, handleSubmit: any, login: any }) {

  return (
    <>
      <main style={{ padding: '1rem 0' }}>
        <form
          onSubmit={handleSubmit(async (data: any) => {
            await login({
              email: data.email,
              password: data.password,
            });
          })
          }>

          <table>
            <tbody>
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

            {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
            {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}

            <tr>
              <td></td>
              <td>
                <button type="submit">로그인 하기</button>
              </td>
            </tr>
            </tbody>
          </table>
        </form>
      </main>
    </>
  );
}
