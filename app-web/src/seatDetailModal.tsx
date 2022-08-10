import Modal from './components/Modal';
import Details from './components/details';

export default function SeatDetailsModal({ open, onClose, seatDetails, accessToken }:any) {
  const [ userName, seatNumber, date, checkIn, checkOut ] = seatDetails;

  if (!accessToken && !userName) {
    return <div>로그인 후 이용해주세요.</div>;
  }

  return (
    <>

      {!accessToken && userName}
      <Modal open{...open} onclose={onClose}>
        <Details {...userName} {...seatNumber} {...checkIn} {...checkOut} />
      </Modal>

        if(accessToken)
          if(!userName)
          <Modal open{...open} onclose={onClose}>
            <div>{seatNumber}번 좌석</div>
            {/*<ReservationButton onClick={} accessToken={accessToken} isMe={} userName={userName} />*/}
            {/*예약할때 이동할때  기존의 자리를 없애야하는데 .delete(기존자리) 기존자리는 백엔드에서 알아서 삭제해주기로했다*/}
            {/*내가 넘길껀 토큰이랑 좌석 번호만*/}
            {/*그럼 여기는 mutation 예약하기 전에쓰던로직그대로 .*/}

          </Modal>

          if(userName)
            if(isMe)
            <Modal open{...open} onclose={onClose}>
              <Details {...userName} {...seatNumber} {...checkIn} {...checkOut} />
              {/*<ReservationButton onClick={} accessToken={accessToken} isMe={} userName={userName} />*/}
              {/*기존 자리예약 취소는 userName이였는데 토큰 넘기면 같은 사람인지 아닌지 확인해서 그리고 isMe 면 ?? 토큰 + isMe?*/}
            </Modal>

            if(!isMe)
            <Modal open{...open} onclose={onClose}>
              <Details {...userName} {...seatNumber} {...checkIn} {...checkOut} />
              {/*<ReservationButton onClick={} accessToken={accessToken} isMe={} userName={userName} />*/}
              {/*토큰을 보냈는데 isMe가 아닐때 예약 ..*/}
            </Modal>
    </>
  );
}
