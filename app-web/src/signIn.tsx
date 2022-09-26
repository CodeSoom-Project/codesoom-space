export default function SignIn({ register, errors, handleSubmit, error, mutate }:
{ register: any, errors: any, handleSubmit: any, error: any, mutate: any }) {

  return (
    <>
      <main style={{ padding: '1rem 0' }}>
        <form
          onSubmit={handleSubmit(async (data: any) => {
            await mutate({
              email: data.email,
              password: data.password,
            });
          })
          }>

          <table>
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
          </table>
        </form>
      </main>
    </>
  );
}
