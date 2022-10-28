import { useEffect } from 'react';

import { useAppDispatch, useAppSelector } from '../../hooks';

import { loadReservations, savePage } from '../../redux/reservationsSlice';

import ReservationsList from '../../components/ReservationsList';

import columns from '../../data/columns';

export default function ReserVationsContainer() {
  const dispatch = useAppDispatch();

  const { pagination, reservations } = useAppSelector((store) => store.reservations);

  const { page } = pagination;

  const handleChangePage = (e: React.ChangeEvent<unknown>, value: number): void => {
    dispatch(savePage(value));
  };

  useEffect(() => {
    dispatch(loadReservations());
  }, [page]);

  return (
    <ReservationsList
      pagination={pagination}
      reservations={reservations}
      columns={columns}
      onChange={handleChangePage} />
  );
}
