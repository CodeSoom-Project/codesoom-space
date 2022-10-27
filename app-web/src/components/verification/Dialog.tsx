import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

interface Props {
  title: string,
  message: string
}

export default function AlertDialog({ title, message }: Props) {
  const [open, setOpen] = React.useState(true);

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <Dialog
        open={open}
        onClose={handleClose}
        fullWidth
      >
        <DialogTitle >
          {title}
        </DialogTitle>
        <DialogContent>
          <DialogContentText >
            {message}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>
              확인
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
