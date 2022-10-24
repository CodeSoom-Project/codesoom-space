import * as React from 'react';

import { useDispatch } from 'react-redux';

import styled from '@emotion/styled';

import { useQuery } from 'react-query';

import dayjs from 'dayjs';

import { CircularProgress, LinearProgress } from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';

import { useAppSelector } from '../../hooks';

import { get } from '../../utils';

import {
  saveIsDetail,
  saveDate,
  saveContent,
  saveIsUpdate,
} from '../../redux/reservationsSlice';

import { getReservations, reservationsKeys } from '../../services/reservations';

const TextFieldWrap = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'space-around',
  width: '20rem',
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
  loading: boolean;
  updateLoading: boolean;
  open: boolean;
  onClose: React.ReactEventHandler;
  onApply: React.ReactEventHandler;
  onUpdate: React.ReactEventHandler;
}

function DetailReservationDialog({ onClose }: {
  onClose: React.ReactEventHandler
}) {
  const dispatch = useDispatch();

  const { id } = useAppSelector(get('reservations'));

  const { isLoading, data } = useQuery(
    reservationsKeys.reservationsById(id),
    () => getReservations(id), { retry: 1 });

  if (isLoading) {
    return <CircularProgress />;
  }
  const { date, content } = data;

  const changeReservationMode = () => {
    dispatch(saveIsDetail(false));
    dispatch(saveIsUpdate(true));
    dispatch(saveContent(content));
    dispatch(saveDate(date));
  };


  return (
    <>
      <Title>
        예약 상세보기
      </Title>
      <TextFieldWrap>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DatePicker
            label="방문일자"
            onChange={() => { }}
            value={date === null ? null : dayjs(date)}
            disabled={true}
            renderInput={(params) => {
              return (<TextField {...params} />);
            }}
          />
        </LocalizationProvider>

        <Text
          label="계획"
          value={content}
          variant="outlined"
          multiline
          rows={3}
          fullWidth
          disabled={true}
        />
        <ButtonWrap>
          <Button
            onClick={onClose}
            variant="outlined"
            size="small"
          >
          닫기
          </Button>
          <Button
            onClick={changeReservationMode}
            variant="contained"
            size="small"
          >
          수정
          </Button>
        </ButtonWrap>
      </TextFieldWrap>
    </>
  );
}

function ApplyReservationDialog({ onClose, onApply, onUpdate }: {
  onClose: React.ReactEventHandler;
  onApply: React.ReactEventHandler;
  onUpdate: React.ReactEventHandler;
}) {
  const dispatch = useDispatch();

  const { isUpdate, date, content } = useAppSelector(get('reservations'));

  const handleChange = (value: dayjs.Dayjs | null) => {
    dispatch(
      saveDate(value === null ? null : dayjs(value).format('YYYY-MM-DD')),
    );
  };

  const handleClick = (e: React.MouseEvent<HTMLElement>) => {
    if (isUpdate) {
      onUpdate(e);
      return;
    }
    onApply(e);
  };

  const characterMaximum = 1000;

  return (
    <>
      <Title>
        {isUpdate && '예약 수정하기'}
        {!isUpdate && '공부방 예약하기'}
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
          inputProps={{ maxLength: characterMaximum }}
          placeholder="계획을 입력해주세요."
          helperText={`${content.length} / ${characterMaximum}`}
          value={content}
          onChange={(e) => {
            dispatch(saveContent(e.target.value));
          }}
          variant="outlined"
          fullWidth
          multiline
          rows={3}
        />

        <ButtonWrap>
          <Button
            onClick={onClose}
            variant="outlined"
            size="small"
          >
            {isUpdate && '수정 취소'}
            {!isUpdate && '닫기'}
          </Button>
          <Button
            onClick={handleClick}
            disabled={!date || !content}
            variant="contained"
            size="small"
          >
            제출
          </Button>
        </ButtonWrap>
      </TextFieldWrap>
    </>
  );
}

export default function ReservationDialog({
  loading,
  updateLoading,
  open,
  onClose,
  onApply,
  onUpdate,
}: Props) {

  const { isDetail } = useAppSelector(get('reservations'));

  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="form-dialog-title"
    >
      {loading && <LinearProgress />}
      {updateLoading && <LinearProgress />}

      {isDetail
        ? (
          <DetailReservationDialog
            onClose={onClose}
          />
        )
        : (
          <ApplyReservationDialog
            onClose={onClose}
            onApply={onApply}
            onUpdate={onUpdate}
          />
        )}
    </Dialog >
  );
}
