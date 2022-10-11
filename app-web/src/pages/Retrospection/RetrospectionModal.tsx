import styled from '@emotion/styled';
import { Button, Dialog, IconButton, TextField } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { useState } from 'react';

const Wrap = styled.div`
  padding:3rem;
  display: flex;
  flex-direction: column;
`;

const ButtonWrap = styled.div`
  display: flex;
  justify-content: flex-end;

  button:nth-child(1){
      margin-right:1rem
  }
`;

const RetrospectionModal: React.FC = () => {
  const characterLimit = 500;
  const [values, setValues] = useState('');

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    setValues(event.target.value);
  };

  return (
    <Dialog
      open
    >
      <Wrap>
        <IconButton
          sx={{
            display: 'flex',
            justifyContent: 'flex-end',
            padding: 0,
          }}
          color="primary"
          aria-label="close dialog"
          component="label">
          <CloseIcon />
        </IconButton>
        <TextField
          label="회고"
          variant="outlined"
          multiline
          rows={3}
          placeholder="회고를 입력해주세요."
          fullWidth
          style={{ margin: '1rem 3rem 1rem 0' }}
          inputProps={{ maxlength: characterLimit, minLength: 100 }}
          value={values}
          helperText={`${values.length}/${characterLimit}`}
          onChange={handleChange}
        />
        <ButtonWrap>
          <Button variant='outlined'>
            취소
          </Button>
          <Button variant='outlined'>
            제출
          </Button>
        </ButtonWrap>
      </Wrap>
    </Dialog>
  );
};

export default RetrospectionModal;
