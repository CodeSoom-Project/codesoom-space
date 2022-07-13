import Button from './components/Button';

function Item({
  userName, seatNumber, date, checkIn, checkOut,
}: any) {
  return (
    <li>
      {userName}
      -
      {seatNumber}
      -
      {date}
      -
      {checkIn}
      -
      {checkOut}
      <Button onClick={onClick}>
        예약 취소하기
      </Button>
    </li>
  );
}

export default Item;
