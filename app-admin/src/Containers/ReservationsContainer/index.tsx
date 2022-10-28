import { useEffect } from 'react';

import { useAppDispatch, useAppSelector } from '../../hooks';

import { loadReservations, savePage } from '../../redux/reservationsSlice';

import ReservationsList from '../../components/ReservationsList';

import columns from '../../data/columns';

import Modal from '../../component/Modal';

import {
  loadRetrospectives,
  toggleRetrospectivesModal,
} from '../../redux/retrospectivesSlice';

export default function ReserVationsContainer() {
  const dispatch = useAppDispatch();

  const {
    pagination,
    reservations,
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

  useEffect(() => {
    dispatch(loadReservations());
  }, [page]);

  const modalHandler = () => {
    dispatch(toggleRetrospectivesModal());
  };

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
