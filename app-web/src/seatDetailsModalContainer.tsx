import SeatDetailModal from "./seatDetailModal";
import {useAppSelector} from "./hooks";
import {useQuery} from "react-query";
import {getSeatDetails} from "./api";

function SeatDetailsModalContainer({open, onClose}:any) {

  const seatNumber = useAppSelector((state) =>state.reservation.seatNumber)
  //상세조회 요청시 사용할 좌석 번호.

  const {
    data: seatDetails,
  } = useQuery(
    ['seatDetails'],
    getSeatDetails({seatNumber}),
  );
  // 상세조회 요청 get 할 useQuery 쓰면 될듯 .

  return (
      <SeatDetailModal open={open} onClose={onClose} seatDetails={seatDetails}/>
  );
}

export default SeatDetailsModalContainer;
