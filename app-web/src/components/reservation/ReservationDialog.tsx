import * as React from 'react';
import { useDispatch } from 'react-redux';

import styled from '@emotion/styled';

import { useQuery } from 'react-query';

import dayjs from 'dayjs';

import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';

import { useAppSelector } from '../../hooks';

import { get } from '../../utils';

import { saveDate, saveContent } from '../../redux/reservationsSlice';

import { getReservations, reservationsKeys } from '../../services/reservations';

const TextFieldWrap = styled.div({
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
  margin: '1rem 3rem 0 0',
});

const Text = styled(TextField)({
  margin: '1rem 3rem 1rem 0',
});

interface Props {
  open: boolean,
  onClose: React.ReactEventHandler,
  onApply: React.ReactEventHandler
}

export default function ReservationDialog({ open, onClose, onApply }: Props) {
  const dispatch = useDispatch();

  const { date, content } = useAppSelector(get('reservations'));

  const id = 2;

  const { isLoading, data, isError } = useQuery(
    reservationsKeys.reservationsById(id),
    () => getReservations(id), { retry: 1 });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Error</div>;
  }

  const handleChange = (value: dayjs.Dayjs | null) => {
    dispatch(
      saveDate(value === null ? null : dayjs(value).format('YYYY-MM-DD')),
    );
  };

  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="form-dialog-title"
    >
      <Title>
        공부방 예약하기
      </Title>

      <TextFieldWrap>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="방문일자"
            value={date === null ? null : dayjs(date)}
            onChange={(value) => handleChange(value)}
            renderInput={(params) => {
              return (<TextField {...params} />);
            }}
          />
        </LocalizationProvider>

        <Text
          label="계획"
          value={content}
          onChange={(e) => {
            dispatch(saveContent(e.target.value));
          }}
          variant="outlined"
          multiline
          rows={3}
          placeholder="계획을 입력해주세요."
          fullWidth
        />
        <ButtonWrap>
          <Button variant="outlined" size="small" onClick={onClose}>
            취소
          </Button>
          <Button disabled={!date || !content} onClick={onApply} variant="contained" size="small">
            제출
          </Button>
        </ButtonWrap>
      </TextFieldWrap>
    </Dialog >
  );
}
