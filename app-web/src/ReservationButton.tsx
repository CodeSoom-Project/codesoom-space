import Button from './components/Button';

export default function ReservationButton({ onClick, onDelete, isMySeat }:any) {

  if (!isMySeat) {
    return <Button onClick={onClick}>예약하기</Button>;
  }

  return (
    <>
      <Button onClick={onClick}>예약하기</Button>
      <Button onClick={onDelete}>예약취소</Button>
    </>
  );
}
