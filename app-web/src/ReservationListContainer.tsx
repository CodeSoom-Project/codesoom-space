import { useMutation, useQuery, useQueryClient } from 'react-query';
import { apis, deleteReservationFn } from './services/api';
import ReservationList from './ReservationList';

export default function ReservationListContainer() {
  const queryClient = useQueryClient();

  const {
    data: reservationData,
    isFetching,
  } = useQuery(
    ['reservation'],
    apis.getReservation,
  );

  const deleteSeat = async ({ seatNumber }: { seatNumber: string | number }) => {
    const deleteSeatResult = await deleteReservationFn({ seatNumber });
    return deleteSeatResult;
  };

  const { mutate: deleteReservation } = useMutation('deleteMutation', deleteSeat, {
    onSuccess(data) {
      queryClient.invalidateQueries(['reservation']);
      queryClient.invalidateQueries(['getSeatList']);
    },
    onError(error: any) {
      console.error(error);
    },
  });

  return (
    <div>
      {isFetching && '예약 정보를 받아오고 있습니다...'}
      <ReservationList
        reservations={reservationData}
        onClickDelete={deleteReservation}
      />
    </div>
  );
}
