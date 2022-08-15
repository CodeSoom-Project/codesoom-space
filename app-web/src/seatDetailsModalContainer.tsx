import SeatDetailModal from './seatDetailModal';
import { useAppSelector } from './hooks';
import { useQuery } from 'react-query';
import { getSeatDetails } from './services/api';

export default function SeatDetailsModalContainer({ open, onClose }:any) {
  const seatNumber = useAppSelector((state) =>state.reservation.seatNumber);

  const {
    data: seatDetails,
  } = useQuery(
    ['seatDetails', seatNumber],
    ()=>getSeatDetails({ seatNumber }),
  );

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
      />
  );
}

