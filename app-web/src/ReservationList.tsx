import Item from './ReservationItem';

function ReservationList({reservations, onClickDelete}: any) {
  return (
    <ol>
      {reservations?.data.map((reservation: any) => (
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
