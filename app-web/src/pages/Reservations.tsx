import styled from '@emotion/styled';

import { Button, LinearProgress } from '@mui/material';

import { useMutation } from 'react-query';

import { useAppDispatch, useAppSelector } from '../hooks';

import { get } from '../utils';

import { toggleReservationsModal } from '../redux/reservationsSlice';
import { toggleRetrospectModal } from '../redux/retrospectionSlice';

import ReservationDialog from '../components/reservation/ReservationDialog';
import ReservationsTable from '../components/reservations/ReservationsTable';
import RetrospectionModal from './Retrospection';

import { fetchReservation } from '../services/reservations';
import { fetchRetrospection } from '../services/retrospection';

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

  const { isOpenReservationsModal, date, plan } = useAppSelector(get('reservations'));
  const { isOpenRetrospectModal, retrospections } = useAppSelector(get('retrospections'));

  const onClicktoggleReservationsModal = () => {
    dispatch(toggleReservationsModal());
  };

  const onClicktoggleRetrospectModal = () => {
    dispatch(toggleRetrospectModal());
  };

  const { mutate: reservationMutate, isLoading } = useMutation(fetchReservation, {
    onSuccess: () => {
      alert('예약이 신청되셨습니다.');
    },
    onError: () => {
      alert('예약이 실패하였습니다 다시 신청해주세요.');
    },
  });

  const onClickApplyReservation = () => {
    reservationMutate({
      date,
      plan,
    });
  };

  if (isLoading) {
    return (
      <LinearProgress />
    );
  }

  const { mutate: retrospectiveMutate } = useMutation(fetchRetrospection, {
    onSuccess: () => {
      alert('회고가 제출되었습니다.');
    },
    onError: () => {
      alert('회고 제출에 실패했습니다. 다시 시도해주세요.');
    },
  });

  const onClickApplyRetrospection = () => {
    retrospectiveMutate({
      id: 1,
      retrospections: retrospections,
    });
  };

  return (
    <Container>
      <ReservationDialog
        open={isOpenReservationsModal}
        onClose={onClicktoggleReservationsModal}
        onApply={onClickApplyReservation}
      />
      <RetrospectionModal
        open={isOpenRetrospectModal}
        onClose={onClicktoggleRetrospectModal}
        onApply={onClickApplyRetrospection}
      />

      <Wrap>
        <Header>
          <Title>예약내역</Title>

          <Button
            style={{ fontSize: '2rem' }}
            onClick={onClicktoggleReservationsModal}
          >예약하기
          </Button>
        </Header>

        <ReservationsTable
          onOpenReservationModal={onClicktoggleReservationsModal}
          onOpenRetrospectModal={onClicktoggleRetrospectModal}
        />
      </Wrap>
    </Container >
  );
}