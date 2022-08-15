import Modal from './components/Modal';
import Details from './components/details';
import ReservationButton from './ReservationButton';
import SelectCheckTime from './SelectCheckTime';


export default function SeatDetailsModal({ open, onClose, seatDetails, onClick, onDelete }:any) {
  const [ userName, seatNumber, date, checkIn, checkOut ] = seatDetails;

  return (
    <>
      <Modal open{...open} onclose={onClose}>
        <Details {...userName} {...seatNumber} {...checkIn} {...checkOut} />
        <SelectCheckTime children={'예약 시작 시간'} />
        <SelectCheckTime children={'예약 종료 시간'} />
        <ReservationButton onClick={onClick} onDelete={onDelete} isMySeat={isMySeat} />
      </Modal>
    </>
  );
}
