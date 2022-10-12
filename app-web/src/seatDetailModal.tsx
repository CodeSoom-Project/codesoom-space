import Modal from './components/Modal';
import Detail from './components/details';

export default function SeatDetailModal({ open, onClose, seatNumber, seatDetail }:any) {
  if (!seatDetail) {
    return (
      <>
        <Modal open={open} onClose={onClose} >
          <Detail
            userName=''
            seatNumber={seatNumber}
          />
        </Modal>
      </>
    );
  }

  const { userName } = seatDetail;

  return (
    <>
      <Modal open={open} onClose={onClose} >
        <Detail
          userName={userName}
          seatNumber={seatNumber}
        />
      </Modal>
    </>
  );
}
