import * as React from 'react';
import { useDispatch } from 'react-redux';

import styled from '@emotion/styled';

import dayjs from 'dayjs';

import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';

import { useAppSelector } from '../../hooks';
import { saveDate, savePlan } from '../../redux/reservationsSlice';


const TextFieldWrap = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-around',
  padding: '1.5rem',
});


interface PropsType {
  open : boolean,
  onClose : React.ReactEventHandler
}

export default function ReservationDialog({ open, onClose }: PropsType) {
  const dispatch = useDispatch();

  const { date, plan } = useAppSelector(store => store.reservations);

  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="form-dialog-title"
    >
      <DialogTitle>공부방 예약하기</DialogTitle>

      <TextFieldWrap>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="방문일자"
            value={date === null ? null : dayjs(date)}
            onChange={(value) => {
              dispatch(
                saveDate(value === null ? null : dayjs(value).format('YYYY-MM-DD')),
              );
            }}
            renderInput={(params) => {
              return (<TextField {...params} />);
            }}
          />
        </LocalizationProvider>

        <TextField
          label="계획"
          value={plan}
          onChange={(e): any => {
            dispatch(savePlan(e.target.value));
          }}
          variant="outlined"
          multiline
          rows={3}
          placeholder="계획을 입력해주세요."
          fullWidth
          style={{ marginTop: '1rem' }}
        />
      </TextFieldWrap>

      <DialogActions>
        <Button variant="contained" size="small">
          제출
        </Button>
        <Button variant="outlined" size="small">
          수정
        </Button>
      </DialogActions>
    </Dialog>
  );
}
