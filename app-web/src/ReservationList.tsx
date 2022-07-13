function ReservationList({ data }: any) {
  return (
    <div>
      <ul>
        {data?.map((reservation: any) => (
          <li
            key={reservation.seatNumber}
          >
            {reservation.userName}
            {reservation.seatNumber}
            {reservation.checkIn}
            {reservation.checkOut}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ReservationList;
