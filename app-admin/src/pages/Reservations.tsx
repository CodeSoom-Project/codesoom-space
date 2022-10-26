import Modal from '../component/Modal';

export default function Reservations() {
  return (
    <div>
      <Modal
        open={true}
        title="계획내용"
        content={'오늘은 자바스크립트를 공부해보겠습니다 \n git 공부를 해보겠습니다'}
        onClose={() => {}}
        onClick={() => {}}
      />
    </div>
  );
}
