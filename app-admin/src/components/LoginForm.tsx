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

export default function LoginForm() {

  return (
    <Wrapper>
      <form>
        <LabelWrapper>
          <div>
            <TextField id="outlined-basic" label="아이디" variant="outlined"/>
          </div>
          <TextField
            id="outlined-basic"
            label="비밀번호"
            variant="outlined"
            type="password"
          />
          <ButtonWrapper>
            <Button sx={ButtonStyles} type="submit" variant="outlined">로그인</Button>
          </ButtonWrapper>
        </LabelWrapper>
      </form>
    </Wrapper>
  );
}
