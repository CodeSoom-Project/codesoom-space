import Modal from './components/Modal';
import Detail from './components/details';
import ReservationButton from './ReservationButton';
import SelectCheckTime from './SelectCheckTime';


export default function SeatDetailModal({ open, onClose, seatNumber, seatDetail, onClick, onDelete, onChangeCheckIn, onChangeCheckOut }:any) {
  const { userName, , date, checkIn, checkOut } = seatDetail;

  return (
    <>
      <Modal open={open} onclose={onClose}>
        <Detail userName={userName} seatNumber={seatNumber} checkIn={checkIn} checkOut={checkOut} />
        <SelectCheckTime children={'예약 시작 시간'} onChangeCheckIn={onChangeCheckIn} />
        <SelectCheckTime children={'예약 종료 시간'} onChangeCheckOut={onChangeCheckOut} />
        {/*<ReservationButton onClick={onClick} onDelete={onDelete} isMySeat={isMySeat} />*/}
      </Modal>
    </>
  );
}
