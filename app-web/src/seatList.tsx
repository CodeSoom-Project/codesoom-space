import {useQuery} from "react-query";
import {getSeatList} from "./api";

const SeatList = () => {
  const query = useQuery(['getSeatList'], getSeatList)
  
  return (
    <div>
      {query?.data?.map(seat => (
        <div key={seat.seatNumber}>
          {seat.seatNumber}
          {seat.userName}
        </div>
      ))}
    </div>
  );
};

export default SeatList;
