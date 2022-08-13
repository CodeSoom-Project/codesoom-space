import Button from './Button';

export default function ReservationButton({ onClick, accessToken, isMe, userName }:any) {
  if (accessToken && !userName) {
    return (
      <>
        <Button onClick={onClick}>예약 하기</Button>
      </>
    );
  }
  if (accessToken && userName && isMe) {
    return (
      <>
        <Button onClick={onClick}>예약 취소</Button>
      </>
    );
  }
  if (accessToken && userName && !isMe)  {
    return (
      <>
        <Button onClick={onClick}>예약 하기</Button>
      </>
    );
  }
}
