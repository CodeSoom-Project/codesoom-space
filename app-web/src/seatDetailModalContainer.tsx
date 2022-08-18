import SeatDetailModal from './seatDetailModal';
import { useAppDispatch, useAppSelector } from './hooks';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { bookingSeatFn, deleteReservationFn, getSeatDetail } from './services/api';
import { changeReservationCheckIn, changeReservationCheckOut } from './ReservationSlice';

export default function SeatDetailModalContainer({ open, onClose }:any) {
  const dispatch = useAppDispatch();

  const seatNumber = useAppSelector((state) =>state.reservation.seatNumber);

  const queryClient = useQueryClient();

  const {
    data: seatDetail,
  } = useQuery(
    ['seatDetail', seatNumber],
    ()=>getSeatDetail({ seatNumber }),
  );

  const handleChangeCheckIn = ({ checkIn }:any) => {
    dispatch(changeReservationCheckIn({ checkIn }));
  };

  const handleChangeCheckOut = ({ checkOut }:any) => {
    dispatch(changeReservationCheckOut({ checkOut }));
  };

  const deleteSeat = async ({ seatNumber }: { seatNumber: string | number }) => {
    const deleteSeatResult = await deleteReservationFn({ seatNumber });
    return deleteSeatResult;
  };

  const { mutate: deleteReservation } = useMutation('deleteMutation', deleteSeat, {
    onSuccess(data) {
      queryClient.invalidateQueries(['reservation']);
      queryClient.invalidateQueries(['seatDetail']);
    },
    onError(error: any) {
      console.error(error);
    },
  });

  const bookingSeat = async ({ seatNumber }: { seatNumber: string | number }) => {
    const deleteSeatResult = await bookingSeatFn({ seatNumber });
    return deleteSeatResult;
  };

  const { mutate: bookingMutation } = useMutation('bookingMutation', bookingSeat, {
    onSuccess(data) {
      queryClient.invalidateQueries(['reservation']);
      queryClient.invalidateQueries(['seatDetail']);
    },
    onError(error: any) {
      console.error(error);
    },
  });


  return (
    <SeatDetailModal
      open={open}
      onClose={onClose}
      seatNumber={seatNumber}
      seatDetail={seatDetail}
      onClick={bookingMutation}
      onDelete={deleteReservation}
      onChangeCheckIn={handleChangeCheckIn}
      onChangeCheckOut={handleChangeCheckOut}
    />
  );
}
