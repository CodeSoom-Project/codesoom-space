function Item({
  userName, seatNumber, date, checkIn, checkOut,
}: any) {
  return (
    <li>
      {userName}
      {seatNumber}
      {date}
      {checkIn}
      {checkOut}
    </li>
  );
}

export default Item;
