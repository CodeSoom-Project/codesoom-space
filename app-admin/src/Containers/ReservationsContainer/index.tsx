import { useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

import { useAppDispatch, useAppSelector } from '../../hooks';

import {
  loadReservations,
  savePage,
  setErrorMessage,
} from '../../redux/reservationsSlice';

import ReservationsList from '../../components/ReservationsList';

import columns from '../../data/columns';

import Modal from '../../component/Modal';

import {
  loadRetrospectives,
  toggleRetrospectivesModal,
} from '../../redux/retrospectivesSlice';

export default function ReserVationsContainer() {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const {
    pagination,
    reservations,
    errorMessage,
  } = useAppSelector((store) => store.reservations);

  const {
    showRetrospectivesModal,
    content,
  } = useAppSelector((store) => store.retrospectives);

  const { page } = pagination;

  const handleChangePage = (
    e: React.ChangeEvent<unknown>,
    value: number): void => {
    dispatch(savePage(value));
  };

  const handleClickDetailRetrospectives = (id: number) => {
    dispatch(loadRetrospectives(id));
  };

  const modalHandler = () => {
    dispatch(toggleRetrospectivesModal());
  };

  useEffect(() => {
    dispatch(loadReservations());
  }, [page]);

  useEffect(() => {
    if (errorMessage) {
      alert(errorMessage);
      navigate('/');
    }
  }, [errorMessage]);

  useEffect(() => {
    return () => {
      dispatch(setErrorMessage(''));
    };
  }, [errorMessage]);

  return (
    <>
      <ReservationsList
        pagination={pagination}
        reservations={reservations}
        columns={columns}
        open={showRetrospectivesModal}
        onClick={handleClickDetailRetrospectives}
        onChange={handleChangePage}
      />
      <Modal
        title='회고 내용'
        content={content}
        open={showRetrospectivesModal}
        onClick={modalHandler}
      />
    </>
  );
}
