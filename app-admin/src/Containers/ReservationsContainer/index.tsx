import { useEffect } from 'react';

import { useAppDispatch, useAppSelector } from '../../hooks';

import { loadReservations, savePage } from '../../redux/reservationsSlice';

import ReservationsList from '../../components/ReservationsList';

import columns from '../../data/columns';
import Modal from '../../component/Modal';
import { toggleRetrospectivesModal } from '../../redux/retrospectivesSlice';

export default function ReserVationsContainer() {
  const dispatch = useAppDispatch();

  const { pagination, reservations } = useAppSelector((store) => store.reservations);
  const { isOpenRetrospectivesModal } = useAppSelector((store) => store.retrospectives);

  const { page } = pagination;

  const handleChangePage = (e: React.ChangeEvent<unknown>, value: number): void => {
    dispatch(savePage(value));
  };

  useEffect(() => {
    dispatch(loadReservations());
  }, [page]);

  const handleClickOpenModal = () => {
    console.log('dd');
    dispatch(toggleRetrospectivesModal());
  };

  console.log(isOpenRetrospectivesModal);

  return (
    <>
      <ReservationsList
        pagination={pagination}
        reservations={reservations}
        columns={columns}
        onClick={handleClickOpenModal}
        onChange={handleChangePage} />
      <Modal
        title='test'
        content='test'
        open={isOpenRetrospectivesModal}
        onClick={handleClickOpenModal}
      />
    </>
  );
}
