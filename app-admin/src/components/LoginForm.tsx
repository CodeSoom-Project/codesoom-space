import styled from '@emotion/styled';

import { Button, TextField } from '@mui/material';

const Wrapper = styled.div({
  display: 'flex',
  justifyContent: 'center',
  alignContent: 'center',
  width: '100%',
  height: '100%',
  padding: '3rem 0',
});

const Form = styled.form({
  display: 'block',
});

const LabelWrapper = styled.div({
  display: 'flex',
  flexDirection: 'column',
});

const ButtonWrapper = styled.div({
  textAlign: 'center',
  marginTop: '1rem',
});

export default function LoginForm() {
  return (
    <Wrapper>
      <Form>
        <LabelWrapper>
          <TextField id="outlined-basic" label="아이디" variant="outlined"/>
          <TextField
            margin="dense"
            id="outlined-basic"
            label="비밀번호"
            variant="outlined"
            type="password"
          />
        </LabelWrapper>
        <ButtonWrapper>
          <Button type="submit" variant="outlined">로그인</Button>
        </ButtonWrapper>
      </Form>
    </Wrapper>
  );
}
