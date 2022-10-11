import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  reservationFields: {
    seatNumber: '',
    userName: '',
    checkIn: '',
    checkOut: '',
  },
  seatNumber: 0,
};

export const reservationSlice = createSlice({
  name: 'reservation',
  initialState,
  reducers: {
    changeReservationDetailsSeatNumber(state, { payload: { seatNumber } }) {
      return {
        ...state,
        seatNumber,
      };
    },
  },
});

export const {
  changeReservationDetailsSeatNumber,
} = reservationSlice.actions;

export default reservationSlice.reducer;
