import SeatDetailModal from './seatDetailModal';
import { useAppSelector } from './hooks';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { bookingSeatFn, deleteReservationFn, getSeatDetail } from './services/api';

export default function SeatDetailModalContainer({ open, onClose }:any) {
  const seatNumber = useAppSelector((state) =>state.reservation.seatNumber);

  const queryClient = useQueryClient();

  const { data: seatDetail } = useQuery(
    ['seatDetail', seatNumber],
    ()=>getSeatDetail(seatNumber),
    {
      enabled: !!seatNumber,
    },
  );

  const deleteSeat = async ({ seatNumber }: { seatNumber:number }) => {
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

  const bookingSeat = async ({ seatNumber, checkIn, checkOut }: { seatNumber:number, checkIn:string, checkOut:string }) => {
    const bookingSeatResult = await bookingSeatFn({ seatNumber, checkIn, checkOut });
    return bookingSeatResult;
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
    />
  );
}

