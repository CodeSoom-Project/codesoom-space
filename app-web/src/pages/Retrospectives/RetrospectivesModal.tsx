import { useDispatch } from 'react-redux';

import styled from '@emotion/styled';

import { Button, Dialog, IconButton, TextField } from '@mui/material';

import CloseIcon from '@mui/icons-material/Close';

import { writeRetrospectives } from '../../redux/retrospectivesSlice';

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

interface Props {
  open: boolean,
  onClose: React.ReactEventHandler,
  onApply: React.ReactEventHandler,
}

const RetrospectivesModal: React.FC<Props> = ({ open, onClose, onApply }: Props) => {
  const dispatch = useDispatch();

  const { retrospectives } = useAppSelector((state) => state.retrospectives);

  const characterMinimum = 100;
  const characterMaximum = 1000;

  const isMinimum = retrospectives.length > characterMinimum;

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    dispatch(writeRetrospectives(event.target.value));
  };

  return (
    <Dialog
      open={open}
      onClose={onClose}
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
          component="label"
          onClick={onClose}
        >
          <CloseIcon />
        </IconButton>
        <TextField
          inputProps={{ maxLength: characterMaximum, minLength: characterMinimum }}
          label='회고'
          placeholder='회고를 입력해주세요.'
          helperText={`${retrospectives.length} /${characterMaximum}`}
          value={retrospectives}
          onChange={handleChange}
          style={{ margin: '1rem 3rem 1rem 0' }}
          variant='outlined'
          fullWidth
          multiline
          rows={3}
        />
        <ButtonWrap>
          <Button onClick={onClose} variant='outlined' size='small'>
            취소
          </Button>
          <Button
            variant='contained' size='small'
            disabled={!isMinimum}
            onClick={onApply}
          >
            제출
          </Button>
        </ButtonWrap>
      </Wrap >
    </Dialog >
  );
};

export default RetrospectivesModal;
