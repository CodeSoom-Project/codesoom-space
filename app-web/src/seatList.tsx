import {useQuery} from "react-query";
import {getSeatList} from "./api";
import {useState} from "react";
import {useAppDispatch} from "./hooks";
import {changeReservationDetailsSeatNumber} from "./ReservationSlice";

const BUTTON_WRAPPER_STYLES = {
  position:'relative',
  zIndex: 1
}

const OTHER_CONTENT_STYLES = {
  position:'relative',
  zIndex: 2,
  backgroundColor: 'red',
  padding: '10px'
}

function SeatList() {
  const dispatch = useAppDispatch();

  const [isOpen, setIsOpen] = useState(false)

  const {data : seatList} = useQuery(['getSeatList'], getSeatList)

  const handleChange =() => {
    dispatch(changeReservationDetailsSeatNumber)
  }

  const handleOpen = () => {
    setIsOpen(true)
  }

  const handleClose = () => {
    setIsOpen(false)
  }

  const handleClick = () => {
    handleChange()
    handleOpen()
  }

  return (
    <div>
      {seatList?.data?.map(seat => (
        <div style={BUTTON_WRAPPER_STYLES}>
          <button onClick={handleClick}>
            <div key={seat.seatNumber}>
            {seat.seatNumber}
            {seat.userName}
            </div>
          </button>
        </div>
      ))}

      {/*<SeatDetailsModalContainer*/}
      {/*  open={isOpen}*/}
      {/*  onClose={handleClose}*/}
      {/*/>*/}

    </div>
  );
}

export default SeatList;
