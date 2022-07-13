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
      refetchOnWindowFocus: 'always',
    },
  );

  if (isLoading) {
    return '예약 정보를 불러오는중입니다.';
  }

  if (isError) {
    return '무언가 잘 못 돼었습니다.';
  }

  function handleClickDeleteReservation(seatNumber, userName) {

    // TODO: delete
    //  api 만들어서 엑시오스로 delete ㅆ고
    //  fetch한다 react-query로 mutation
    // 그리고 fetch 한 데이터 보여주기 .
    //
    //

  }

  return (
    <div>
      {isFetching && '예약 정보를 받아오고 있습니다...'}
      <ReservationList
        reservations={data}
        onClickDelete={handleClickDeleteReservation}
      />
    </div>
  );
}

export default ReservationListContainer;
