import {useMutation, useQuery, useQueryClient} from 'react-query';
import {apis, deleteReservationFn} from './api';
import ReservationList from './ReservationList';
import {useAppSelector} from "./hooks";


function ReservationListContainer() {
  const reservationFields = useAppSelector((state) => state.reservation.reservationFields);
  const {
    seatNumber,
    userName,
  }: { seatNumber: string | number; userName: string } = reservationFields;

  const queryClient = useQueryClient();

  const {
    data: reservationData,
    isFetching,
  } = useQuery(
    'reservation',
    apis.getReservation,
  );

  const deleteSeat = async ({seatNumber, userName}: { seatNumber: string | number, userName: string }) => {
    const deleteSeatResult = await deleteReservationFn({seatNumber, userName})
    return deleteSeatResult
  }

  const {mutate: deleteReservation} = useMutation('deleteMutation', deleteSeat, {
    onSuccess(data) {
      queryClient.invalidateQueries('reservation');
    },
    onError(error: any) {
      console.error(error);
    },
  });

  // const handleDeleteReservation = () => {
  //   console.log('삭제중')
  // }

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

export default ReservationListContainer;
