import styled from '@emotion/styled';

import { Button, LinearProgress } from '@mui/material';

import { useMutation } from 'react-query';

import { useAppDispatch, useAppSelector } from '../hooks';

import { get } from '../utils';

import { checkIsDetail, toggleReservationsModal } from '../redux/reservationsSlice';
import { toggleRetrospectModal } from '../redux/retrospectivesSlice';

import ReservationDialog from '../components/reservation/ReservationDialog';
import ReservationsTable from '../components/reservations/ReservationsTable';

import RetrospectivesModal from './Retrospectives';

import { fetchReservation } from '../services/reservations';
import { fetchRetrospectives } from '../services/retrospectives';

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

  const { isOpenReservationsModal, isDetail, date, plan } = useAppSelector(get('reservations'));
  const { isOpenRetrospectModal, retrospectives } = useAppSelector(get('retrospectives'));

  const onClicktoggleReservationsModal = () => {
    dispatch(toggleReservationsModal());
    dispatch(checkIsDetail(false));
  };

  const onClickToggleReservationDetailsModal = () => {
    dispatch(toggleReservationsModal());
    dispatch(checkIsDetail(true));
  };


  const onClicktoggleRetrospectModal = () => {
    dispatch(toggleRetrospectModal());
  };

  const { mutate: reservationMutate, isLoading: reservationIsLoading } = useMutation(fetchReservation, {
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

  const { mutate: retrospectiveMutate, isLoading: retrospectiveIsLoading } = useMutation(fetchRetrospectives, {
    onSuccess: () => {
      alert('회고가 제출되었습니다.');
    },
    onError: () => {
      alert('회고 제출에 실패했습니다. 다시 시도해주세요.');
    },
  });

  const onClickApplyRetrospectives = () => {
    retrospectiveMutate({
      id: 1,
      retrospectives: retrospectives,
    });
  };

  if (reservationIsLoading || retrospectiveIsLoading) {
    return (
      <LinearProgress />
    );
  }

  return (
    <Container>
      <ReservationDialog
        open={isOpenReservationsModal}
        onClose={onClicktoggleReservationsModal}
        onApply={onClickApplyReservation}
        isDetail={isDetail}
      />

      <RetrospectivesModal
        open={isOpenRetrospectModal}
        onClose={onClicktoggleRetrospectModal}
        onApply={onClickApplyRetrospectives}
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
          onOpenReservationModal={onClickToggleReservationDetailsModal}
          onOpenRetrospectModal={onClicktoggleRetrospectModal}
        />
      </Wrap>
    </Container >
  );
}
