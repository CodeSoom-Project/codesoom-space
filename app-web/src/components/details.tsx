interface DetailType {
  seatNumber: string;
  userName: string;
}

export default function Detail({ seatNumber, userName }: DetailType) {

  return (
    <>
      <div>{seatNumber}번 좌석</div>
      <div>{userName} 님이 이용중</div>
    </>

  );

}
