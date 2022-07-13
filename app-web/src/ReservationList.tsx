import Item from './ReservationItem';

function ReservationList({ reservations }: any) {
  return (
    <ol>
      {reservations?.map((reservation: any) => (
        <Item
          key={reservation.userName}
        >
          userName=
          {reservation.userName}

          seatNumber=
          {reservation.seatNumber}

          date=
          {reservation.date}

          checkIn=
          {reservation.checkIn}

          checkOut=
          {reservation.checkOut}
        </Item>
      ))}
    </ol>
  );
}

export default ReservationList;
