import { useDispatch } from 'react-redux';

import { useAppSelector } from '../../hooks';

import { get } from '../../utils';

import { saveRetrospectives } from '../../redux/retrospectivesSlice';

import { useQuery } from 'react-query';

import {
  getRetrospective,
  retrospectivesKeys,
} from '../../services/retrospectives';

import styled from '@emotion/styled';

import {
  Button,
  CircularProgress,
  Dialog,
  DialogTitle,
  TextField }
  from '@mui/material';

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

const TextTitle = styled.h1({
  marginBottom: '1rem',
  fontSize: '1.3rem',
});

const TextBox = styled.div({
  display: 'flex',
  flexDirection: 'column',
  maxHeight: '20rem',
  overflow: 'auto',
  marginBottom: '2rem',

  'p': {
    margin: '0 0 1rem 0',
  },
});

interface Props {
  open: boolean,
  onClose: React.ReactEventHandler,
  onApply: React.ReactEventHandler,
}

function DetailReservationDialog({ onClose }:{ onClose: React.ReactEventHandler }) {
  const { id } = useAppSelector(get('retrospectives'));

  const { isLoading, data } = useQuery(
    retrospectivesKeys.retrospectivesById(id),
    () => getRetrospective(id), { retry: 1 });

  if (isLoading) {
    return <CircularProgress />;
  }

  return (
    <Wrap>
      <TextTitle>회고</TextTitle>
      <TextBox>{data.content.split('\n').map((line:string) => (<p>{line}</p>))}</TextBox>
      <ButtonWrap>
        <Button variant="outlined" size="small" onClick={onClose}>
            취소
        </Button>
      </ButtonWrap>
    </Wrap>
  );
}

function ApplyReservationDialog({ onClose, onApply }:{
  onClose : React.ReactEventHandler;
  onApply : React.ReactEventHandler;
}) {
  const dispatch = useDispatch();

  const { retrospectives } = useAppSelector(get('retrospectives'));

  const characterMinimum = 100;
  const characterMaximum = 1000;

  const isMinimum = retrospectives.length > characterMinimum;

  const handleChange: React.ChangeEventHandler<HTMLInputElement> = (event) => {
    dispatch(saveRetrospectives(event.target.value));
  };

  return (
    <>
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
    </>
  );
}



export default function ReservationDialog({ open, onClose, onApply }: Props) {
  const { isDetail } = useAppSelector(get('retrospectives'));

  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="form-dialog-title"
    >
      {isDetail
        ? <DetailReservationDialog onClose={onClose} />
        : <ApplyReservationDialog onClose={onClose} onApply={onApply} />}
    </Dialog >
  );
}