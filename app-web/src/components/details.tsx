interface DetailsType {
  seatNumber: string;
  userName: string;
  checkIn: string | number ;
  checkOut: string | number ;
}

function Details({seatNumber, userName, checkIn, checkOut }:DetailsType) {

  return (
    <>
      <div>{seatNumber}번 좌석</div>
      <div>{userName} 님이 이용중</div>
      <div>{checkIn} - {checkOut}</div>
    </>

    )

}
export default Details
