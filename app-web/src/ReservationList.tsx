import Item from './ReservationItem';

function ReservationList({ data, onClickDelete }: any) {
  return (
    <ol>
      {data?.data.map((reservation: any) => (
        <Item
          key={reservation.seatNumber}
          userName={reservation.userName}
          seatNumber={reservation.seatNumber}
          date={reservation.date}
          checkIn={reservation.checkIn}
          checkOut={reservation.checkOut}
          onClickDelete={onClickDelete}
        />
      ))}
    </ol>
  );
}

export default ReservationList;
