import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  reservationFields: {
    seatNumber: '',
    userName: '',
    checkIn: '',
    checkOut: '',
  },
  seatNumber: '',
  checkIn: '',
  checkOut: '',
};

export const reservationSlice = createSlice({
  name: 'reservation',
  initialState,
  reducers: {
    changeReservationFields(state, { payload: { name, value } }) {
      return {
        ...state,
        reservationFields: {
          ...state.reservationFields,
          [name]: value,
        },
      };
    },
    changeReservationDetailsSeatNumber(state, { payload: { seatNumber } }) {
      return {
        ...state,
        seatNumber,
      };
    },
    changeReservationCheckIn(state, { payload: { checkIn } }) {
      return {
        ...state,
        checkIn,
      };
    },

    changeReservationCheckOut(state, { payload: { checkOut } }) {
      return {
        ...state,
        checkOut,
      };
    },
  },
});

export const {
  changeReservationFields,
  changeReservationDetailsSeatNumber,
  changeReservationCheckIn,
  changeReservationCheckOut,
} = reservationSlice.actions;

export default reservationSlice.reducer;
