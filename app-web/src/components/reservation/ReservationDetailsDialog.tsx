import * as React from 'react';

import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';

interface PropsType {
  open : boolean,
  onClose : React.ReactEventHandler
}

export default function ReservationDetailsDialog({ open, onClose }: PropsType) {
  return (
    <Dialog
      open={open}
      onClose={onClose}
      aria-labelledby="form-dialog-title"
    >
      <DialogTitle>10월 1일</DialogTitle>
      <DialogContent>
        <DialogContentText>
          git 공부하기
        </DialogContentText>
      </DialogContent>
    </Dialog>
  );
}
