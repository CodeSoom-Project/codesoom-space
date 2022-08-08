import Modal from "./components/Modal";

function SeatDetailsModal({open, onClose}:any) {
  return (
    //if 만약 좌석클릭햇는데 아무도 예약을 하지않았더라면
    //이미 다른 자리 예약했다면 기존자리 삭제후 이동 그후 예약가능~
    // 다른 자리 예약 안했으면 예약 가능~

    <Modal open={open} onClose={onClose}>

    </Modal>

    //if 누가 예약을 했더라면
    //1 그게 나다
    //2 그게 다른 사람이다
  );
};

export default SeatDetailsModal;
