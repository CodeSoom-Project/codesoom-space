import SeatDetailModal from './seatDetailModal';
import { useAppSelector } from './hooks';
import { useQuery } from 'react-query';
import { getSeatDetails } from './api';

export default function SeatDetailsModalContainer({ open, onClose }:any) {
  const seatNumber = useAppSelector((state) =>state.reservation.seatNumber);
  const accessToken = useAppSelector((state) => state.auth.accessToken);

  const {
    data: seatDetails,
  } = useQuery(
    ['seatDetails', seatNumber],
    ()=>getSeatDetails({ seatNumber }),
  );

  return (
      <SeatDetailModal open={open} onClose={onClose} seatDetails={seatDetails} accessToken={accessToken} />
  );
}

