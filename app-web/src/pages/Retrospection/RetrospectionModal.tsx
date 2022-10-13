import { useDispatch } from 'react-redux';

import styled from '@emotion/styled';

import { Button, Dialog, IconButton, TextField } from '@mui/material';

import CloseIcon from '@mui/icons-material/Close';

import { writeRetrospection } from '../../redux/retrospectionSlice';

import { useAppSelector } from '../../hooks';

const Wrap = styled.div({
  display: 'flex',
  padding: '3rem',
  flexDirection: 'column',
});

const ButtonWrap = styled.div({
  display: 'flex',
  justifyContent: 'flex-end',

  'button: nth-of-type(1)': {
    marginRight: '1rem',
  },
});

const RetrospectionModal: React.FC = () => {
  const dispatch = useDispatch();
  const { retrospections } = useAppSelector((state) => state.retrospections);

  const characterLimit = 1000;
  const isMinimum = retrospections.length > 100;

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    dispatch(writeRetrospection(event.target.value));
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
          color='primary'
          aria-label='close dialog'
          component='label'>
          <CloseIcon />
        </IconButton>
        <TextField
          inputProps={{ maxLength: characterLimit, minLength: 100 }}
          label='회고'
          placeholder='회고를 입력해주세요.'
          helperText={`${retrospections.length} /${characterLimit}`}
          value={retrospections}
          onChange={handleChange}
          style={{ margin: '1rem 3rem 1rem 0' }}
          variant='outlined'
          fullWidth
          multiline
          rows={3}
        />
        <ButtonWrap>
          <Button variant='outlined' size='small'>
            취소
          </Button>
          <Button
            variant='contained' size='small'
            disabled={!isMinimum}
          >
            제출
          </Button>
        </ButtonWrap>
      </Wrap >
    </Dialog >
  );
};

export default RetrospectionModal;
