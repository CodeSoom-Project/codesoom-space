import styled from '@emotion/styled';

import { Button, TextField } from '@mui/material';

import { LoginFields } from '../../redux/authSlice';

const Wrapper = styled.div({
  display: 'flex',
  justifyContent: 'center',
  alignContent: 'center',
  width: '100%',
  height: '100%',
  padding: '3rem 0',
});

const LabelWrapper = styled.div({
  'div:not(:first-of-type)': {
    marginTop: '8px',
  },
});

const ButtonWrapper = styled.div({
  textAlign: 'center',
  marginTop: '1rem',
});

const ButtonStyles = {
  width: '100%',
  padding: '10px',
};

type Props = {
  handleSubmit: React.MouseEventHandler<HTMLButtonElement>;
  onChange: ({ name, value }: { name: string, value: string })=> void;
  fields: LoginFields;
};

const LoginForm: React.FC<Props> = ({
  fields: { email, password },
  handleSubmit,
  onChange,
}) => {
  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (e) => {
    const { target: { name, value } } = e;

    onChange({ name, value });
  };
  return (
    <Wrapper>
      <form>
        <LabelWrapper>
          <div>
            <TextField
              id="outlined-basic"
              label="아이디"
              variant="outlined"
              value={email}
              name="email"
              onChange={handleChange}
            />
          </div>
          <TextField
            id="outlined-basic"
            label="비밀번호"
            variant="outlined"
            value={password}
            name="password"
            type="password"
            onChange={handleChange}
          />
          <ButtonWrapper>
            <Button
              sx={ButtonStyles}
              variant="outlined"
              onClick={handleSubmit}
            >
              로그인
            </Button>
          </ButtonWrapper>
        </LabelWrapper>
      </form>
    </Wrapper>
  );
};

export default LoginForm;
