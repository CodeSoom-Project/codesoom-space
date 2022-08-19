import ReactDom from 'react-dom';
import { useRef } from 'react';
import useOutsSideClick from '../hook/useOutSideClick';

const MODAL_STYLES = {
  position: 'fixed',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  backgroundColor: '#FFF',
  padding: '50px',
  zIndex: 1000,
};

const OVERLAY_STYLES = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: 'rgba(0,0,0, .7)',
  zIndex: 1000,
};

export default function Modal({ open, children, onClose }:any) {
  if (!open) return null;
  const modalRef = useRef(null);
  const handleClose = () => {
    onClose?.();
  };

  useOutsSideClick(modalRef, handleClose );

  return ReactDom.createPortal(
    <div ref={modalRef}>
      <div style={OVERLAY_STYLES} />
      <div style={MODAL_STYLES}>
        <button onClick={handleClose}>창 닫기</button>
        {children}
      </div>
    </div>,
    document.getElementById('portal'),
  );
}


