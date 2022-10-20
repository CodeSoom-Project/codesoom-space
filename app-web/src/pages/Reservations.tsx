import styled from '@emotion/styled';

import { Button } from '@mui/material';

import { useMutation, useQuery } from 'react-query';

import { useAppDispatch, useAppSelector } from '../hooks';

import { get } from '../utils';

import { resetReservations, saveIsDetail, saveIsUpdate, toggleReservationsModal } from '../redux/reservationsSlice';
import { resetRetrospectives, toggleRetrospectModal } from '../redux/retrospectivesSlice';

import ReservationDialog from '../components/reservation/ReservationDialog';
import ReservationsTable from '../components/reservations/ReservationsTable';
import RetrospectivesModal from './Retrospectives';

import { fetchReservation, getReservation, updateReservation } from '../services/reservations';
import { fetchRetrospectives, updateRetrospectives } from '../services/retrospectives';

const Container = styled.div({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
  width: '100%',
});

const Wrap = styled.div({
  width: '90%',
  paddingTop: '1rem',
});

const Header = styled.div({
  display: 'flex',
  justifyContent: 'space-between',
  width: '100%',
  marginBottom: '1rem',
});

const Title = styled.h1({
  fontSize: '2rem',
  margin: '0',
});


export default function Reservations() {

  const dispatch = useAppDispatch();

  const { isOpenReservationsModal, isUpdate, date, content, id } = useAppSelector(get('reservations'));
  const { isOpenRetrospectModal, retrospectives } = useAppSelector(get('retrospectives'));

  const onClickToggleReservationsModal = () => {
    if (isUpdate) {
      dispatch(saveIsDetail(true));
      dispatch(saveIsUpdate(false));
      return;
    }

    dispatch(toggleReservationsModal());
    dispatch(resetReservations());
  };

  const onClickToggleRetrospectModal = () => {
    dispatch(toggleRetrospectModal());
    dispatch(resetRetrospectives());
  };



  const { mutate: updateReservationMutate, isLoading: updateReservationisLoading } = useMutation(updateReservation, {
    onSuccess: () => {
      alert('예약이 수정되었습니다.');
      onClickToggleReservationsModal();
      dispatch(saveIsUpdate(false));
    },
    onError: () => {
      alert('수정이 실패하였습니다 다시 시도해주세요.');
    },
  });

  const { mutate: reservationMutate, isLoading: reservationIsLoading } = useMutation(fetchReservation, {
    onSuccess: () => {
      alert('예약이 신청되셨습니다.');
      onClickToggleReservationsModal();
    },
    onError: () => {
      alert('예약이 실패하였습니다 다시 신청해주세요.');
    },
  });


  const onClickUpdateRservation = () => {
    updateReservationMutate({
      id,
      date,
      content,
    });
  };

  const onClickApplyReservation = () => {

    reservationMutate({
      date,
      content,
    });
  };

  const { mutate: retrospectiveMutate } = useMutation(fetchRetrospectives, {
    onSuccess: () => {
      alert('회고가 제출되었습니다.');
      onClickToggleRetrospectModal();
    },
    onError: () => {
      alert('회고 제출에 실패했습니다. 다시 시도해주세요.');
    },
  });

  const onClickApplyRetrospectives = () => {
    retrospectiveMutate({
      id,
      content: retrospectives,
    });
  };

  const { mutate: updateRetrospectiveMutate } = useMutation(updateRetrospectives, {
    onSuccess: () => {
      alert('회고가 수정되었습니다.');
      onClickToggleRetrospectModal();
    },
    onError: () => {
      alert('회고 수정에 실패했습니다. 다시 시도해주세요.');
    },
  });

  const onClickUpdateRetrospectives = () => {
    updateRetrospectiveMutate({
      id,
      content: retrospectives,
    });
  };

  const { isLoading, data, isError } = useQuery('reservations', getReservation, {
    retry: 1,
  });

  return (
    <Container>
      <ReservationDialog
        loading={reservationIsLoading}
        updateLoading={updateReservationisLoading}
        open={isOpenReservationsModal}
        onClose={onClickToggleReservationsModal}
        onApply={onClickApplyReservation}
        onUpdate={onClickUpdateRservation}
      />

      <RetrospectivesModal
        open={isOpenRetrospectModal}
        onClose={onClickToggleRetrospectModal}
        onApply={onClickApplyRetrospectives}
        onUpdate={onClickUpdateRetrospectives}
      />

      <Wrap>
        <Header>
          <Title>예약내역</Title>

          <Button
            style={{ fontSize: '2rem' }}
            onClick={() => {
              onClickToggleReservationsModal();
              dispatch(saveIsDetail(false));
            }}
          >예약하기
          </Button>
        </Header>

        <ReservationsTable
          onOpenReservationModal={onClickToggleReservationsModal}
          onOpenRetrospectModal={onClickToggleRetrospectModal}
          isLoading={isLoading}
          reservations={data?.reservations}
          isError={isError}
        />
      </Wrap>
    </Container >
  );
}
