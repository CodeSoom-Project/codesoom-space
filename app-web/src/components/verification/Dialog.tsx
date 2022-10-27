import React from 'react';

import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from '@mui/material';

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
    <Dialog
      open={open}
      onClose={handleClose}
      fullWidth
    >
      <DialogTitle>
        {title}
      </DialogTitle>
      <DialogContent>
        <DialogContentText>
          {message}
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button
          type="button"
          onClick={handleClose}
        >
          확인
        </Button>
      </DialogActions>
    </Dialog>
  );
}
