import Button from './components/Button';

function Item({
  userName, seatNumber, onClickDelete,
}: any) {
  return (
    <li>
      {userName}
      -
      {seatNumber}
      <Button onClick={() => onClickDelete({ seatNumber, userName })}>
        예약 취소하기
      </Button>
    </li>
  );
}

export default Item;
