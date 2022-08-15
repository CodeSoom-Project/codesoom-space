import Modal from './components/Modal';
import Details from './components/details';
import ReservationButton from './ReservationButton';

export default function SeatDetailsModal({ open, onClose, seatDetails, onClick, onDelete }:any) {
  const [ userName, seatNumber, date, checkIn, checkOut ] = seatDetails;

  return (
    <>
      <Modal open{...open} onclose={onClose}>
        <Details {...userName} {...seatNumber} {...checkIn} {...checkOut} />
        <ReservationButton onClick={onClick} onDelete={onDelete} isMySeat={isMySeat} />
      </Modal>
    </>
  );
}
