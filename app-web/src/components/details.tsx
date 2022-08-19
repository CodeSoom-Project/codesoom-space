interface DetailType {
  seatNumber: string;
  userName: string;
  checkIn: string | number ;
  checkOut: string | number ;
}

export default function Detail({ seatNumber, userName, checkIn, checkOut }:DetailType) {

  return (
    <>
      <div>{seatNumber}번 좌석</div>
      <div>{userName} 님이 이용중</div>
      <div>{checkIn} - {checkOut}</div>
    </>

  );

}
