import SeatDetailModal from './seatDetailModal';
import { useAppDispatch, useAppSelector } from './hooks';
import { useQuery } from 'react-query';
import { getSeatDetails } from './services/api';
import { changeReservationCheckIn, changeReservationCheckOut } from './ReservationSlice';

export default function SeatDetailsModalContainer({ open, onClose }:any) {
  const dispatch = useAppDispatch();

  const seatNumber = useAppSelector((state) =>state.reservation.seatNumber);

  const {
    data: seatDetails,
  } = useQuery(
    ['seatDetails', seatNumber],
    ()=>getSeatDetails({ seatNumber }),
  );

  const handleChangeCheckIn = ({ checkIn }:any) => {
    dispatch(changeReservationCheckIn({ checkIn }));
  };

  const handleChangeCheckOut = ({ checkOut }:any) => {
    dispatch(changeReservationCheckOut({ checkOut }));
  };

  const handleClickReservationButton = () => {
    return;
  };

  const handleDeleteReservation = () => {
    return;
  };

  return (
      <SeatDetailModal
        open={open}
        onClose={onClose}
        seatDetails={seatDetails}
        onClick={handleClickReservationButton}
        onDelete={handleDeleteReservation}
        onChangeCheckIn={handleChangeCheckIn}
        onChangeCheckOut={handleChangeCheckOut}
      />
  );
}

