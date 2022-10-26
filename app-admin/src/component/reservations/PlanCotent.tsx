import * as React from 'react';
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

export default function PlanContent() {
  return (
    <Dialog
      open={true}
      //   onClose={handleClose}
    >
      <DialogTitle >
      </DialogTitle>
      <DialogContent>
        <DialogContentText >
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button ></Button>
        <Button autoFocus>
        </Button>
      </DialogActions>
    </Dialog>
  );
}