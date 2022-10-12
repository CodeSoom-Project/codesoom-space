import SeatDetailModal from './seatDetailModal';
import { useAppSelector } from './hooks';
import { useMutation, useQuery, useQueryClient } from 'react-query';
import { cancelReservation, getSeatDetail, seatReservation } from './services/api';

export default function SeatDetailModalContainer({ open, onClose }:any) {
  const seatNumber = useAppSelector((state) => state.reservation.seatNumber);

  const queryClient = useQueryClient();

  const { data: seatDetail } = useQuery(
    ['seatDetail', seatNumber],
    () => getSeatDetail(seatNumber),
    {
      enabled: !!seatNumber,
    },
  );

  // eslint-disable-next-line @typescript-eslint/no-shadow
  const cancel = async ({ seatNumber }: { seatNumber:number }) => {
    const deleteSeatResult = await cancelReservation({ seatNumber });
    return deleteSeatResult;
  };

  const { mutate: cancelMutation } = useMutation('deleteMutation', cancel, {
    onSuccess: () => {
      queryClient.invalidateQueries(['reservation']);
      queryClient.invalidateQueries(['seatDetail']);
    },
    onError(error: any) {
      console.error(error);
    },
  });

  // eslint-disable-next-line @typescript-eslint/no-shadow
  const reservation = async ({ seatNumber }: { seatNumber:number }) => {
    const bookingSeatResult = await seatReservation({ seatNumber });
    return bookingSeatResult;
  };

  const { mutate: reservationMutation } = useMutation('reservationMutation', reservation, {
    onSuccess: () => {
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
      onClick={reservationMutation}
      onDelete={cancelMutation}
    />
  );
}

