import Button from './components/Button';

export default function Item({
  userName, seatNumber, date, checkIn, checkOut, onClickDelete,
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
      <Button onClick={() => onClickDelete({ seatNumber, userName })}>
        예약 취소하기
      </Button>
    </li>
  );
}
