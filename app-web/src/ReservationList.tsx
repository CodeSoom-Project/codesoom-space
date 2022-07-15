import Item from './ReservationItem';

function ReservationList({ data, onClickDelete }: any) {
  return (
    <ol>
      {data?.data.map((reservation: any) => (
        <Item
          key={reservation.seatNumber}
          userName={reservation.userName}
          seatNumber={reservation.seatNumber}
          onClickDelete={onClickDelete}
        />
      ))}
    </ol>
  );
}

export default ReservationList;
