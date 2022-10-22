import { Button } from '@mui/material';

import styled from '@emotion/styled';

const FormWrapper = styled.form({
  display: 'flex',
  alignItems: 'center',
  padding: '1rem 0',
  justifyContent: 'center',
  '@media (max-width: 375px)': {
    flexDirection: 'column',
  },
});

const LabelWrapper = styled.div({
  'div:not(:first-of-type)': {
    marginTop: '8px',
  },
});

const Label = styled.label({
  display: 'inline-block',
  width: '100px',
});

const ButtonWrapper = styled.div({
  marginLeft: '12px',
  '@media (max-width: 375px)': {
    marginTop: '12px',
  },
});

interface Props {
  register: any;
  errors: any;
  handleSubmit: any;
  signUp: any;
}

export default function SignUp({ register, errors, handleSubmit, signUp }: Props) {
  return (
    <FormWrapper
      onSubmit={handleSubmit(async (data: any) => {
        await signUp({
          email: data.email,
          password: data.password,
          name: data.name,
        });
      })
      }>

      <LabelWrapper>
        <div>
          <Label htmlFor="name">이름</Label>
          <input {...register('name', {
            required: true,
          })} type="text" id="name" />
        </div>

        <div>
          <Label htmlFor="email">Email</Label>
          <input {...register('email', {
            required: true,
          },
          )} type="email" id="email" />
        </div>

        <div>
          <Label htmlFor="password">비밀번호</Label>

          <input {...register('password', {
            required: true,
          })} type="password" id="password" />
        </div>

        <div>
          <Label htmlFor="passwordMatch">비밀번호 확인</Label>
          <input {...register('passwordMatch', {
            required: true,
          })} type="password" id="passwordMatch" />
          {errors.passwordMatch && (
            <>
              <p>{errors.passwordMatch?.message}</p>
              {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
              {errors.email?.type === 'required' && '이메일을 입력 해 주세요'}
              {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}
            </>
          )}
        </div>
      </LabelWrapper>

      <ButtonWrapper>
        <Button type="submit" variant="outlined">회원가입하기</Button>
      </ButtonWrapper>
    </FormWrapper>
  );
}
