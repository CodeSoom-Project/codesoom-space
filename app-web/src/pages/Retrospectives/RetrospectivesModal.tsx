import { useDispatch } from 'react-redux';

import styled from '@emotion/styled';

import { Button, Dialog, DialogTitle, TextField } from '@mui/material';

import { saveRetrospectives } from '../../redux/retrospectivesSlice';

import { useAppSelector } from '../../hooks';
import { get } from '../../utils';

const Wrap = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-around',
  padding: '1.5rem',
});

const ButtonWrap = styled.div({
  display: 'flex',
  justifyContent: 'flex-end',

  'button: nth-of-type(1)': {
    marginRight: '1rem',
  },
});

const Title = styled(DialogTitle)({
  marginBottom: '0',
  paddingBottom: '0',
});

const Text = styled(TextField)({
  margin: '1rem 3rem 1rem 0',
});

interface Props {
  open: boolean,
  onClose: React.ReactEventHandler,
  onApply: React.ReactEventHandler,
}

const RetrospectivesModal: React.FC<Props> = ({ open, onClose, onApply }: Props) => {
  const dispatch = useDispatch();

  const { retrospectives } = useAppSelector(get('retrospectives'));

  const characterMinimum = 100;
  const characterMaximum = 1000;

  const isMinimum = retrospectives.length > characterMinimum;

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    dispatch(saveRetrospectives(event.target.value));
  };

  return (
    <Dialog
      open={open}
      onClose={onClose}
    >
      <Title>
        회고 작성하기
      </Title>

      <Wrap>
        <Text
          inputProps={{ maxLength: characterMaximum, minLength: characterMinimum }}
          placeholder='회고를 입력해주세요.'
          helperText={`${retrospectives.length} /${characterMaximum}`}
          value={retrospectives}
          onChange={handleChange}
          variant='outlined'
          fullWidth
          multiline
          rows={3}
        />
        <ButtonWrap>
          <Button variant='outlined' size='small' onClick={onClose}>
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
