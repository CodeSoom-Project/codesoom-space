import {useQuery} from "react-query";
import {getSeatList} from "./api";

const SeatList = () => {
  const {data : seatList} = useQuery(['getSeatList'], getSeatList)

  return (
    <div>
      {seatList?.data?.map(seat => (
        <div key={seat.seatNumber}>
          {seat.seatNumber}
          {seat.userName}
        </div>
      ))}
    </div>
  );
};

export default SeatList;
