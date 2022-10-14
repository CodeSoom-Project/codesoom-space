import styled from '@emotion/styled';
import { Button } from '@mui/material';

const FormWrapper = styled.form({
  display: 'flex',
  alignItems: 'center',
  padding: '1rem 0',
  justifyContent: 'center',
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
});

interface Props {
  register: any;
  errors: any;
  handleSubmit: any;
  login: any;
}

export default function LogIn({ register, errors, handleSubmit, login }: Props) {
  return (
    <FormWrapper
      style={{ padding: '1rem 0' }}
      onSubmit={handleSubmit(async (data: any) => {
        await login({
          email: data.email,
          password: data.password,
        });
      })
      }>

      <LabelWrapper >
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
          {errors.name?.type === 'required' && '이름을 입력 해 주세요'}
          {errors.password?.type === 'required' && '비밀번호를 입력 해 주세요'}
        </div>
      </LabelWrapper>

      <ButtonWrapper>
        <Button type="submit" variant="outlined">로그인</Button>
      </ButtonWrapper>
    </FormWrapper >
  );
}
