import Modal from './components/Modal';
import Detail from './components/details';
import SelectTimeContainer from './SelectTimeContainer';


export default function SeatDetailModal({ open, onClose, seatNumber, seatDetail, onClick, onDelete, isMySeat }:any) {
  if (!seatDetail) return (
    <>
      <Modal open={open} onClose={onClose}>
        <Detail
          userName=''
          seatNumber={seatNumber}
          checkIn=''
          checkOut=''
        />
        <SelectTimeContainer
          seatNumber={seatNumber}
          onClick={onClick}
          onDelete={onDelete}
          isMySeat={isMySeat}
        />
      </Modal>
    </>
  );
  const { userName, seatDetailNumber, date, checkIn, checkOut } = seatDetail;

  return (
    <>
      <Modal open={open} onClose={onClose}>
        <Detail
          userName={userName}
          seatNumber={seatNumber}
          checkIn={checkIn}
          checkOut={checkOut}
        />
        <SelectTimeContainer
          seatNumber={seatNumber}
          onClick={onClick}
          onDelete={onDelete}
          isMySeat={isMySeat}
        />
      </Modal>
    </>
  );
}
