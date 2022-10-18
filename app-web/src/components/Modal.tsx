import ReactDom from 'react-dom';

import Button from './Button';

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

export default function Modal({ open, children, onClose }: any) {
  if (!open) {
    return null;
  }
  const handleClose = () => {
    onClose?.();
  };


  return ReactDom.createPortal(
    <div onClickOutside={handleClose}>
      <div style={OVERLAY_STYLES} />
      <div style={MODAL_STYLES}>
        <Button onClick={onClose}>창 닫기</Button>
        {children}
      </div>
    </div>,
    document.getElementById('portal'),
  );
}


