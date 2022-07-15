import { useQuery } from 'react-query';
import { apis } from './reservationApi';
import ReservationList from './ReservationList';

function ReservationListContainer() {
  const {
    data, isLoading, isError, isFetching,
  } = useQuery(
    'reservation',
    // eslint-disable-next-line no-undef
    apis.getReservation,
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

  // const {mutate} = useMutation(deleteReservation, {
  //   onSuccess: (data) => {
  //     queryClient.invalidateQueries('reservation');
  //   },
  // });

  return (
    <div>
      {isFetching && '예약 정보를 받아오고 있습니다...'}
      <ReservationList
        data={data}
        // onClickDelete={deleteReservationMutation}
      />
    </div>
  );
}

export default ReservationListContainer;
