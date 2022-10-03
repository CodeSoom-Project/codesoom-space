import Modal from './components/Modal';
import Detail from './components/details';
import SelectTimeContainer from './SelectTimeContainer';


export default function SeatDetailModal({ open, onClose, seatNumber, seatDetail, onClick, onDelete }:any) {
  if (!seatDetail) return (
    <>
      <Modal open={open} onClose={onClose} >
        <Detail
          userName=''
          seatNumber={seatNumber}
        />
        <SelectTimeContainer
          seatNumber={seatNumber}
          onClick={onClick}
          onDelete={onDelete}
        />
      </Modal>
    </>
  );
  const { userName } = seatDetail;

  return (
    <>
      <Modal open={open} onClose={onClose} >
        <Detail
          userName={userName}
          seatNumber={seatNumber}
        />
        <SelectTimeContainer
          seatNumber={seatNumber}
          onClick={onClick}
          onDelete={onDelete}
        />
      </Modal>
    </>
  );
}
