import Item from './ReservationItem';

function ReservationList({ data, onClickDelete }: any) {
  return (
    <ol>
      {data?.data.map((reservation: any) => (
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

          onClickDelete=
          {onClickDelete}
        </Item>
      ))}
    </ol>
  );
}

export default ReservationList;
