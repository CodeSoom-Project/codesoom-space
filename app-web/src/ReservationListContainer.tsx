import { useQuery } from 'react-query';
import { getReservation } from './reservationApi';
import ReservationList from './ReservationList';

function ReservationListContainer() {
  const {
    data, isLoading, isError, isFetching,
  } = useQuery(
    'reservation',
    getReservation,
    {
      refetchOnMount: true,
      refetchOnWindowFocus: true,
    },
  );

  if (isLoading) {
    return '예약 정보를 불러오는중입니다.';
  }

  if (isError) {
    return '무언가 잘 못 돼었습니다.';
  }

  // const deleteReservationMutation: any {
  //   // TODO : 예약 삭제 후 fetching
  // }

  return (
    <div>
      {isFetching && '예약 정보를 받아오고 있습니다...'}
      <ReservationList
        reservations={data}
        // onClickDelete={deleteReservationMutation}
      />
    </div>
  );
}

export default ReservationListContainer;
